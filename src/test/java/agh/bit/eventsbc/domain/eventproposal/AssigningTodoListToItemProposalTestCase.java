package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.builders.EventProposalCreatedEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.commands.AssignTodoListToEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalAlreadyHasTodoListEvent;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.events.TodoListAssignedToEventProposalEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by novy on 04.01.15.
 */
public class AssigningTodoListToItemProposalTestCase
        extends EventProposalPreconfiguredTestCase {

    private final EventProposalId eventProposalId = new EventProposalId();
    private final TodoListId todoListId = new TodoListId();

    private EventProposalCreatedEvent eventProposalCreatedEvent;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        eventProposalCreatedEvent = EventProposalCreatedEventBuilder
                .newEventProposalCreatedEvent()
                .eventProposalId(eventProposalId)
                .minimalInterestThreshold(15)
                .build();
    }

    @Test
    public void assignTodoListToEventProposalCommandShouldAssignTodoListToEventProposal() throws Exception {

        fixture
                .given(eventProposalCreatedEvent)
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
        final TodoListId firstTodoListId = new TodoListId();
        final TodoListId secondTodoListId = new TodoListId();

        fixture
                .given(
                        eventProposalCreatedEvent,

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


