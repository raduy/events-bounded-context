package agh.bit.eventsbc.domain.eventproposal.commands;

import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.Value;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class AssignTodoListToEventProposalCommand {

    @TargetAggregateIdentifier
    private final EventProposalId eventProposalId;
    private final TodoListId todoListId;
}
