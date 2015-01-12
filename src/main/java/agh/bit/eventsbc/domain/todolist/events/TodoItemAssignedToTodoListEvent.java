package agh.bit.eventsbc.domain.todolist.events;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Created by novy on 04.01.15.
 */

@Getter
@Accessors(fluent = true)
public class TodoItemAssignedToTodoListEvent {

    private final TodoListId todoListId;
    private final TodoItemId todoItemId;
    private final String description;
    private final LocalDate createdAt;

    public TodoItemAssignedToTodoListEvent(TodoListId todoListId, TodoItemId todoItemId, String description, LocalDate createdAt) {
        this.todoListId = todoListId;
        this.todoItemId = todoItemId;
        this.description = description;
        this.createdAt = createdAt;
    }
}
