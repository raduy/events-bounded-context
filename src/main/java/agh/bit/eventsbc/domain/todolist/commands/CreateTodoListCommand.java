package agh.bit.eventsbc.domain.todolist.commands;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Value;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class CreateTodoListCommand {

    @TargetAggregateIdentifier
    private final TodoListId todoListId;

}
