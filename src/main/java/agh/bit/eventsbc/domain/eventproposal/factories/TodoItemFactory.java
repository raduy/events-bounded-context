package agh.bit.eventsbc.domain.eventproposal.factories;

import agh.bit.eventsbc.domain.todolist.entities.TodoItem;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by novy on 05.01.15.
 */
public class TodoItemFactory {

    public static TodoItem create(TodoItemId id, String content, LocalDate createdAt) {
        return new TodoItem(id, content, createdAt);
    }

    public static TodoItem create(String content, LocalDate createdAt) {
        return TodoItemFactory.create(nextId(), content, createdAt);
    }

    public static TodoItemId nextId() {
        final UUID uuid = UUID.randomUUID();
        return TodoItemId.of(uuid.toString());
    }


}
