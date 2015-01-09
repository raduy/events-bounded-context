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

    private final EventProposalId eventProposalId = EventProposalId.of("1234");
    private final TodoListId todoListId = TodoListId.of("666");

    private EventProposalCreatedEvent eventProposalCreatedEvent;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        eventProposalCreatedEvent = EventProposalCreatedEventBuilder
                .newEventProposalCreatedEvent()
                .eventProposalId(eventProposalId)
                .requiredAcceptanceThreshold(15)
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
        final EventProposalId eventProposalId = EventProposalId.of("1234");
        final TodoListId firstTodoListId = TodoListId.of("666");
        final TodoListId secondTodoListId = TodoListId.of("333");

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


