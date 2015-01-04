package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import org.junit.Test;

/**
 * Created by novy on 04.01.15.
 */
public class EventProposalCreationTestCase extends EventProposalPreconfiguredTestCase {

    @Test
    public void createEventProposalCommandShouldCreateNewEventProposal() throws Exception {
        fixture.given()
                .when(
                        new CreateEventProposalCommand(EventProposalId.of("1234"), "Sample event proposal", EventDescription.of("Sample description"))
                )
                .expectEvents(
                        new EventProposalCreatedEvent(EventProposalId.of("1234"), "Sample event proposal", EventDescription.of("Sample description"))
                );
    }
}
