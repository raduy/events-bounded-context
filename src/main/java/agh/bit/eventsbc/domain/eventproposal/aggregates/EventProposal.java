package agh.bit.eventsbc.domain.eventproposal.aggregates;

import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobject.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobject.EventProposalId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposal extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private EventProposalId eventProposalId;
    private String name;
    private TodoList todoList;
    private EventDescription description;

    private EventProposal() {
    }

    public EventProposal(EventProposalId eventProposalId, String name, EventDescription description) {
        apply(new EventProposalCreatedEvent(eventProposalId, name, description));
    }

    @EventSourcingHandler
    public void onEventProposalCreatedEvent(EventProposalCreatedEvent event) {
        this.setEventProposalId(event.eventProposalId());
        this.setName(event.name());
    }

    private void setEventProposalId(EventProposalId eventProposalId) {
        this.eventProposalId = eventProposalId;
    }

    private void setName(String name) {
        this.name = name;
    }


}
