package agh.bit.eventsbc.domain.eventproposalrequest;

import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.eventproposalrequest.commands.CreateEventProposalRequestCommand;
import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;
import agh.bit.eventsbc.domain.events.eventproposalrequest.EventProposalRequestCreatedEvent;
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
