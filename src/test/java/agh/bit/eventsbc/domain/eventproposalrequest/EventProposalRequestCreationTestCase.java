package agh.bit.eventsbc.domain.eventproposalrequest;

import agh.bit.eventsbc.domain.eventproposalrequest.commands.CreateEventProposalRequestCommand;
import agh.bit.eventsbc.domain.eventproposalrequest.events.EventProposalRequestCreatedEvent;
import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;
import org.junit.Test;

public class EventProposalRequestCreationTestCase extends EventProposalRequestPreconfiguredTestCase {

    @Test
    public void createEventProposalCommandShouldCreateNewEventProposal() throws Exception {
        fixture.given()
                .when(
                        new CreateEventProposalRequestCommand(
                                EventProposalRequestId.of("1234"),
                                "Sample event proposal request",
                                "Sample description",
                                "john@doe.com"
                        )
                )
                .expectEvents(
                        new EventProposalRequestCreatedEvent(
                                EventProposalRequestId.of("1234"),
                                "Sample event proposal request",
                                "Sample description",
                                "john@doe.com"
                        )
                );
    }
}
