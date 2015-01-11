package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.builders.CreateEventProposalCommandBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.EventProposalCreatedEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import org.junit.Test;

/**
 * Created by novy on 04.01.15.
 */
public class EventProposalCreationTestCase extends EventProposalPreconfiguredTestCase {

    private final EventProposalId eventProposalId = EventProposalId.of("123");

    @Test
    public void createEventProposalCommandShouldCreateNewEventProposal() throws Exception {
        fixture.given()
                .when(
                        CreateEventProposalCommandBuilder
                                .newCreateEventProposalCommand()
                                .eventProposalId(eventProposalId)
                                .build()
                )
                .expectEvents(
                        EventProposalCreatedEventBuilder
                                .newEventProposalCreatedEvent()
                                .eventProposalId(eventProposalId)
                                .build()
                );
    }
}
