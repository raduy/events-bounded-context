package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.events.EventCreatedEvent;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

public class Event extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private EventId eventId;
    private String name;
    //@TODO dopisać todoList
    //@TODO dopisać EventDescription

    private Event() {
    }

    public Event(EventId eventId) {
        this.eventId = eventId;
    }

    @EventSourcingHandler
    public void onEventCreatedEvent( EventCreatedEvent eventCreatedEvent ) {
        this.eventId = eventCreatedEvent.eventId;
        this.name = eventCreatedEvent.name;
    }

}
