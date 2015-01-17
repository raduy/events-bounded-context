package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.common.IdentifiedDomainAggregateRoot;
import agh.bit.eventsbc.domain.eventproposal.factories.TodoItemFactory;
import agh.bit.eventsbc.domain.eventproposal.factories.TodoItemIdProvider;
import agh.bit.eventsbc.domain.todolist.entities.TodoItem;
import agh.bit.eventsbc.domain.todolist.events.*;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by novy handle 03.01.15.
 */
public class TodoList extends IdentifiedDomainAggregateRoot<TodoListId> {

    @EventSourcedMember
    private List<TodoItem> todoItems = Lists.newArrayList();

    private TodoList() {
    }

    public TodoList(TodoListId id) {
        apply(new TodoListCreatedEvent(id));
    }

    public void assignTodoItem(TodoItemId todoItemId, String content, LocalDate creationDate) {

        if (alreadyHasTodoItemWith(todoItemId)) {
            apply(new TodoItemNotAssignedToTodoListEvent(id, todoItemId));
            return;
        }

        apply(new TodoItemAssignedToTodoListEvent(
                        id, todoItemId, content, creationDate)
        );
    }

    private boolean alreadyHasTodoItemWith(TodoItemId todoItemId) {
        return todoItems.stream().anyMatch(
                todoItem -> todoItem.matchesId(todoItemId)

        );
    }


    @EventSourcingHandler
    public void on(TodoListCreatedEvent event) {
        this.id = event.todoListId();
    }

    @EventSourcingHandler
    public void on(TodoItemAssignedToTodoListEvent event) {
        final TodoItem item = TodoItemFactory.create(
                event.todoItemId(),
                event.description(),
                event.createdAt()
        );

        todoItems.add(item);
    }

    public void markTodoItemDone(TodoItemId todoItemId) {
        final Optional<TodoItem> todoItemOptional = todoItems.stream()
                .filter(todoItem -> todoItem.matchesId(todoItemId))
                .findFirst();

        final TodoItem todoItem = todoItemOptional.orElseThrow(
                () -> new IllegalArgumentException("TodoItem with given getId not present")
        );

        if (todoItem.markedDone()) {
            apply(new TodoItemNotMarkedDoneEvent(id, todoItemId));
            return;
        }

        apply(new TodoItemMarkedDoneEvent(id, todoItemId));
    }

    public void fulfillWith(TodoListTemplate todoListTemplate, LocalDate creationDate, TodoItemIdProvider provider) {
        if (alreadyHasAnyTodoItem()) {
            apply(new TodoListNotFulfilledWithTemplateEvent(id, todoListTemplate.id()));
            return;
        }

        final List<String> todoItemDescriptions = todoListTemplate.todoItemDescriptions();

        apply(new TodoListFulfilledWithTemplateEvent(
                        id,
                        todoListTemplate.id(),
                        provider.nextIdBatch(todoItemDescriptions.size()),
                        todoItemDescriptions,
                        creationDate
                )
        );
    }

    @EventSourcingHandler
    public void on(TodoListFulfilledWithTemplateEvent event) {
        todoItems.addAll(
                fromDescriptions(event.todoItemIds(), event.todoItemDescriptions(), event.fulfilledAt())
        );
    }

    private Collection<TodoItem> fromDescriptions(List<TodoItemId> ids, List<String> descriptions,
                                                  LocalDate creationDate) {
        return IntStream
                .range(0, ids.size())
                .mapToObj(i -> TodoItemFactory.create(ids.get(i), descriptions.get(i), creationDate))
                .collect(Collectors.toList());
    }

    private boolean alreadyHasAnyTodoItem() {
        return !todoItems.isEmpty();
    }
}
