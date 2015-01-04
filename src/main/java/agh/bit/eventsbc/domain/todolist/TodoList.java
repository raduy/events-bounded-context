package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.entities.TodoItem;
import agh.bit.eventsbc.domain.todolist.events.TodoListCreatedEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Collection;


/**
 * Created by novy handle 03.01.15.
 */
public class TodoList extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private TodoListId todoListId;

    @EventSourcedMember
    private Collection<TodoItem> todoItems = Lists.newArrayList();

    private TodoList() {}

    public TodoList(TodoListId todoListId) {
        apply(new TodoListCreatedEvent(todoListId));
    }

    @EventSourcingHandler
    public void on(TodoListCreatedEvent event) {
        this.todoListId = event.todoListId();
    }


}
