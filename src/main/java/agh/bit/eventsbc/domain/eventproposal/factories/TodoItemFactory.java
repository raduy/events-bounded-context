package agh.bit.eventsbc.domain.eventproposal.factories;

import agh.bit.eventsbc.domain.todolist.entities.TodoItem;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;

import java.time.LocalDate;

/**
 * Created by novy on 05.01.15.
 */
public class TodoItemFactory {

    public static TodoItem create(TodoItemId id, String content, LocalDate createdAt) {
        return new TodoItem(id, content, createdAt);
    }

}
