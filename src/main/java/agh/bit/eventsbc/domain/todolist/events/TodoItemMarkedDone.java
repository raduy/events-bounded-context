package agh.bit.eventsbc.domain.todolist.events;

import agh.bit.eventsbc.domain.eventmaster.valueobjects.EventMasterId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 03.01.15.
 */

@Value
@Accessors(fluent = true)
public class TodoItemMarkedDone {

    private final EventProposalId eventProposalId;
    private final TodoListId todoListId;
    private final EventMasterId eventMasterId;
    private final TodoItemId todoItemId;
}
