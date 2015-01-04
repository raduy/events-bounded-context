package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.commands.AssignTodoListToEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalAlreadyHasTodoListEvent;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.events.TodoListAssignedToEventProposalEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.junit.Test;

/**
 * Created by novy on 04.01.15.
 */
public class AssigningTodoListToItemProposalTestCase
        extends EventProposalPreconfiguredTestCase {

    @Test
    public void assignTodoListToEventProposalCommandShouldAssignTodoListToEventProposal() throws Exception {
        final EventProposalId eventProposalId = EventProposalId.of("1234");
        final TodoListId todoListId = TodoListId.of("666");

        fixture
                .given(
                        new EventProposalCreatedEvent(
                                eventProposalId,
                                "Sample event proposal",
                                EventDescription.of("Sample description")
                        )
                )
                .when(
                        new AssignTodoListToEventProposalCommand(
                                eventProposalId,
                                todoListId
                        )
                )
                .expectEvents(
                        new TodoListAssignedToEventProposalEvent(
                                eventProposalId,
                                todoListId
                        )

                );
    }

    @Test
    public void invokingAssignTodoListToEventProposalCommandOnSameAggregateTwiceShouldFail()
            throws Exception {
        final EventProposalId eventProposalId = EventProposalId.of("1234");
        final TodoListId firstTodoListId = TodoListId.of("666");
        final TodoListId secondTodoListId = TodoListId.of("333");

        fixture
                .given(
                        new EventProposalCreatedEvent(
                                eventProposalId,
                                "Sample event proposal",
                                EventDescription.of("Sample description")
                        ),
                        new TodoListAssignedToEventProposalEvent(
                                eventProposalId,
                                firstTodoListId
                        )
                )
                .when(
                        new AssignTodoListToEventProposalCommand(
                                eventProposalId,
                                secondTodoListId
                        )
                )
                .expectEvents(
                        new EventProposalAlreadyHasTodoListEvent(
                                eventProposalId
                        )

                );
    }
}


