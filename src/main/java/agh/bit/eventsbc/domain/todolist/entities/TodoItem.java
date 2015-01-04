package agh.bit.eventsbc.domain.todolist.entities;

import agh.bit.eventsbc.domain.todolist.events.TodoItemMarkedDone;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.time.LocalDate;

/**
 * Created by novy on 03.01.15.
 */
public class TodoItem extends AbstractAnnotatedEntity {

    private final TodoItemId id;
    private String title;
    private String content;
    private boolean done;
    private LocalDate createdAt;

    public TodoItem(TodoItemId todoItemId, String title, String content, LocalDate createdAt) {
        this.id = todoItemId;
        this.title = title;
        this.content = content;
        this.done = false;
        this.createdAt = createdAt;
    }

    @EventSourcingHandler
    public void on(TodoItemMarkedDone event) {
        if (!id.equals(event.todoItemId())) {
            return;
        }

        markDone();
    }

    private void markDone() {
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public boolean matchesById(TodoItemId anotherId) {
        return id.equals(anotherId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoItem item = (TodoItem) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
