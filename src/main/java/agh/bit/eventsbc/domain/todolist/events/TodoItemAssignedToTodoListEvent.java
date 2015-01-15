package agh.bit.eventsbc.domain.todolist.events;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class TodoItemAssignedToTodoListEvent {

    private final TodoListId todoListId;
    private final TodoItemId todoItemId;
    private final String description;
    private final LocalDate createdAt;

}
