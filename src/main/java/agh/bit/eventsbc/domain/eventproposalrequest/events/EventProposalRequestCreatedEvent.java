package agh.bit.eventsbc.domain.eventproposalrequest.events;

import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class EventProposalRequestCreatedEvent {
    private final EventProposalRequestId id;
    private final String name;
    private final String description;
    private final String author;
}
