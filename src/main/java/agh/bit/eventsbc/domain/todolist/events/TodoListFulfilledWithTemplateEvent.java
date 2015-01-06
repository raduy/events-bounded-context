package agh.bit.eventsbc.domain.todolist.events;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by novy on 06.01.15.
 */

@Value
@Accessors(fluent = true)
public class TodoListFulfilledWithTemplateEvent {

    private final TodoListId todoListId;
    private final TodoListTemplateId todoListTemplateId;
    private final List<TodoItemId> todoItemIds;
    private final List<String> todoItemDescriptions;
    private final LocalDate fulfilledAt;
}
