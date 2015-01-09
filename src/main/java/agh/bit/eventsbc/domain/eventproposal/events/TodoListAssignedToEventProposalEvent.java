package agh.bit.eventsbc.domain.eventproposal.events;

import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class TodoListAssignedToEventProposalEvent {

    private final EventProposalId eventProposalId;
    private final TodoListId todoListId;

}
