package agh.bit.eventsbc.domain.todolisttemplate.events;

import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by novy on 05.01.15.
 */

@Value
@Accessors(fluent = true)
public class TodoListTemplateCreatedEvent {

    private final TodoListTemplateId id;
    private final List<String> todoItemDescriptions;

}
