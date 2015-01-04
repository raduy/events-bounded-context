package agh.bit.eventsbc.domain.todolist.commands;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Value;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.time.LocalDate;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class AssignTodoItemToTodoListCommand {

    private final TodoListId todoListId;
    @TargetAggregateIdentifier
    private final TodoItemId todoItemId;
    private final String title;
    private final String content;
    private final LocalDate createdAt;

}
