package agh.bit.eventsbc.domain.todolist.entities;

import agh.bit.eventsbc.domain.common.IdentifiedDomainEntity;
import agh.bit.eventsbc.domain.todolist.events.TodoItemMarkedDoneEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import lombok.ToString;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.time.LocalDate;

/**
 * Created by novy on 03.01.15.
 */
@ToString
public class TodoItem extends IdentifiedDomainEntity<TodoItemId> {

    private String description;
    private boolean done;
    private LocalDate createdAt;

    public TodoItem(TodoItemId todoItemId, String description, LocalDate createdAt) {
        super(todoItemId);
        this.description = description;
        this.done = false;
        this.createdAt = createdAt;
    }

    @EventSourcingHandler
    public void on(TodoItemMarkedDoneEvent event) {
        if (!matchesId(event.todoItemId())) {
            return;
        }

        markDone();
    }

    private void markDone() {
        this.done = true;
    }

    public boolean markedDone() {
        return done;
    }
}
