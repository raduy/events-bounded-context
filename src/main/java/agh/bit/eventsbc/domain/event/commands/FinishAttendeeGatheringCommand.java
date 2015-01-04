package agh.bit.eventsbc.domain.event.commands;

import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class FinishAttendeeGatheringCommand {
    @TargetAggregateIdentifier
    public final EventId eventId;

    public FinishAttendeeGatheringCommand(EventId eventId) {
        this.eventId = eventId;
    }
}
