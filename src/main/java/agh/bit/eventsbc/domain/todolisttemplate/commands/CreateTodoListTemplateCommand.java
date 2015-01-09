package agh.bit.eventsbc.domain.todolisttemplate.commands;

import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by novy on 06.01.15.
 */

@Value
@Accessors(fluent = true)
public class CreateTodoListTemplateCommand {

    private final TodoListTemplateId id;
    private final List<String> todoItemDescriptions;
}
