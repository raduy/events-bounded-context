package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.entities.TodoItem;
import agh.bit.eventsbc.domain.todolist.events.*;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;


/**
 * Created by novy handle 03.01.15.
 */
public class TodoList extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private TodoListId todoListId;

    @EventSourcedMember
    private Collection<TodoItem> todoItems = Lists.newArrayList();

    private TodoList() {
    }

    public TodoList(TodoListId todoListId) {
        apply(new TodoListCreatedEvent(todoListId));
    }

    public void assignTodoItem(TodoItemId todoItemId, String title,
                               String content, LocalDate creationDate) {

        if (alreadyHasTodoItemWith(todoItemId)) {
            apply(new TodoItemNotAssignedToTodoList(
                            todoListId, todoItemId)
            );

            return;
        }

        apply(new TodoItemAssignedToTodoListEvent(
                        todoListId, todoItemId, title, content, creationDate)
        );
    }

    private boolean alreadyHasTodoItemWith(TodoItemId todoItemId) {
        return todoItems.stream().anyMatch(
                todoItem -> todoItem.matchesId(todoItemId)

        );
    }


    @EventSourcingHandler
    public void on(TodoListCreatedEvent event) {
        this.todoListId = event.todoListId();
    }

    @EventSourcingHandler
    public void on(TodoItemAssignedToTodoListEvent event) {
        final TodoItem item = new TodoItem(
                event.todoItemId(),
                event.title(),
                event.content(),
                event.createdAt()
        );

        todoItems.add(item);
    }

    // todo: consider somehow moving check into TodoItem itself
    public void markTodoItemDone(TodoItemId todoItemId) {
        final Optional<TodoItem> todoItemOptional = todoItems.stream()
                .filter(todoItem -> todoItem.matchesId(todoItemId))
                .findFirst();

        final TodoItem todoItem = todoItemOptional.orElseThrow(
                () -> new IllegalArgumentException("TodoItem with given id not present")
        );

        if (todoItem.markedDone()) {
            apply(new TodoItemNotMarkedDoneEvent(todoListId, todoItemId));
            return;
        }

        apply(new TodoItemMarkedDoneEvent(todoListId, todoItemId));
    }
}
