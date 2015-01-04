package agh.bit.eventsbc.domain.todolist.commands;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class CreateTodoListCommand {

    private final TodoListId todoListId;

}
