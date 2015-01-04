package agh.bit.eventsbc.domain.event.commands;

import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateEventCommand {
    @TargetAggregateIdentifier
    public final EventId eventId;
    public final String name;

    public CreateEventCommand( EventId eventId, String name ) {
        this.eventId = eventId;
        this.name = name;
    }
}
