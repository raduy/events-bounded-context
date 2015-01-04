package agh.bit.eventsbc.domain.common;

import agh.bit.eventsbc.domain.event.Event;
import agh.bit.eventsbc.domain.event.EventCommandHandler;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;

public class FixtureFactory {

    public static FixtureConfiguration onEventPreconfiguredFixture( ) {
        FixtureConfiguration fixture = Fixtures.newGivenWhenThenFixture( Event.class );
        EventCommandHandler commandHandler = new EventCommandHandler( );
        commandHandler.setEventRepository( fixture.getRepository( ) );
        fixture.registerAnnotatedCommandHandler( commandHandler );
        return fixture;
    }

}
