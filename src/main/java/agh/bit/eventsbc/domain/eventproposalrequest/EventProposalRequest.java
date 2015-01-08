package agh.bit.eventsbc.domain.eventproposalrequest;

import agh.bit.eventsbc.domain.common.IdentifiedDomainAggregateRoot;
import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;
import agh.bit.eventsbc.domain.events.eventproposalrequest.EventProposalRequestCreatedEvent;

public class EventProposalRequest extends IdentifiedDomainAggregateRoot<EventProposalRequestId> {
    private EventProposalRequestId id;
    private String name;
    private String description;
    private String author;

    public EventProposalRequest() {
    }

    public EventProposalRequest(EventProposalRequestId id, String name, String description, String author) {
        apply(new EventProposalRequestCreatedEvent(id, name, description, author));

    }
}


