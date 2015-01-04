package agh.bit.eventsbc.domain.common;

import agh.bit.eventsbc.domain.eventproposal.EventProposal;
import agh.bit.eventsbc.domain.eventproposal.commandhandlers.EventProposalCommandHandler;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;

/**
 * Created by novy on 04.01.15.
 */
public class FixtureFactory {

    public static FixtureConfiguration onEventProposalPreconfiguredFixture() {
        FixtureConfiguration fixture = Fixtures.newGivenWhenThenFixture(EventProposal.class);
        EventProposalCommandHandler commandHandler = new EventProposalCommandHandler();
        commandHandler.setEventProposalRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
        return fixture;
    }
}
