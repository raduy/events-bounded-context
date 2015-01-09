package agh.bit.eventsbc.domain.todolist.events;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 06.01.15.
 */
@Value
@Accessors(fluent = true)
public class TodoListNotFulfilledWithTemplateEvent {

    private final TodoListId todoListId;
    private final TodoListTemplateId todoListTemplateId;
}
