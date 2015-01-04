package agh.bit.eventsbc.domain.event.factories;

import agh.bit.eventsbc.domain.event.Event;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class EventFactory {

    public static Event create( EventId eventId, String name ) {
        return new Event( eventId, name );
    }
}
