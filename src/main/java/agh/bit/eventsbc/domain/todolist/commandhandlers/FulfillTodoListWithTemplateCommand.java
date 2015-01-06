package agh.bit.eventsbc.domain.todolist.commandhandlers;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Created by novy on 06.01.15.
 */

@Value
@Accessors(fluent = true)
public class FulfillTodoListWithTemplateCommand {

    private final TodoListId todoListId;
    private final TodoListTemplateId todoListTemplateId;
    private final LocalDate creationDate;

}
