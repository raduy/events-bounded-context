package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.commands.CreateEventCommand;
import agh.bit.eventsbc.domain.event.events.EventCreatedEvent;
import agh.bit.eventsbc.domain.event.events.EventProposalApprovedEvent;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class EventTest {
    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        setUpInfrastructure();
    }

    private void setUpInfrastructure() {
        fixture = Fixtures.newGivenWhenThenFixture(Event.class);

        EventCommandHandler commandHandler = new EventCommandHandler();
        commandHandler.setEventRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void thatCreateEventCommandCreatesNewEvent() throws Exception {
        fixture
                .given(
                        new EventProposalApprovedEvent()
                )
                .when(
                        new CreateEventCommand()
                )
                .expectEvents(
                        new EventCreatedEvent(EventId.valueOf(1L), "Evt description")
                );
    }

}
