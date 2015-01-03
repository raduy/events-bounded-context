package agh.bit.eventsbc.domain.eventproposal.events;

import agh.bit.eventsbc.domain.eventproposal.valueobject.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobject.EventProposalId;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposalCreatedEvent {
    private final EventProposalId eventProposalId;
    private final String name;
    private final EventDescription description;

    public EventProposalCreatedEvent(EventProposalId eventProposalId, String name, EventDescription description) {
        this.eventProposalId = eventProposalId;
        this.name = name;
        this.description = description;
    }

    public EventProposalId eventProposalId() {
        return eventProposalId;
    }

    public String name() {
        return name;
    }

    public EventDescription description() { return description; }
}
