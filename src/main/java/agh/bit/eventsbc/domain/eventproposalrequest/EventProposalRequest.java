package agh.bit.eventsbc.domain.eventproposalrequest;

import agh.bit.eventsbc.domain.common.IdentifiedDomainAggregateRoot;
import agh.bit.eventsbc.domain.eventproposalrequest.events.EventProposalRequestCreatedEvent;
import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

public class EventProposalRequest extends IdentifiedDomainAggregateRoot<EventProposalRequestId> {

    private String name;
    private String description;
    private String author;

    public EventProposalRequest() {
    }

    public EventProposalRequest(EventProposalRequestId id, String name, String description, String author) {
        apply(new EventProposalRequestCreatedEvent(id, name, description, author));
    }

    @EventSourcingHandler
    public void on(EventProposalRequestCreatedEvent event) {
        this.id = event.id();
        this.name = event.name();
        this.description = event.description();
        this.author = event.author();
    }
}


