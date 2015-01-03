package agh.bit.eventsbc.domain.eventproposal.aggregates;

import agh.bit.eventsbc.domain.eventproposal.commandhandlers.EventProposalCommandHandler;
import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobject.EventProposalId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class EventProposalTest  {

    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(EventProposal.class);
        EventProposalCommandHandler commandHandler = new EventProposalCommandHandler();
        commandHandler.setEventProposalRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void createEventProposalCommandShouldCreateNewEventProposal() throws Exception {
        fixture.given()
                .when(
                        new CreateEventProposalCommand(EventProposalId.of("1234"), "Sample event proposal")
                )
                .expectEvents(
                        new EventProposalCreatedEvent(EventProposalId.of("1234"), "Sample event proposal")
                );
    }
}