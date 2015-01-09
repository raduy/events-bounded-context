package agh.bit.eventsbc.domain.eventproposal.builders;

import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;

/**
 * Created by novy on 09.01.15.
 */

public class EventProposalCreatedEventBuilder {

    private EventProposalId eventProposalId = EventProposalId.of("default ID");
    private String name = "default name";
    private EventDescription description = EventDescription.of("default description");
    private int requiredAcceptanceThreshold = 15;

    public EventProposalCreatedEventBuilder() {}

    public static EventProposalCreatedEventBuilder newEventProposalCreatedEvent() {
        return new EventProposalCreatedEventBuilder();
    }

    public EventProposalCreatedEventBuilder withEventProposalId(EventProposalId eventProposalId) {
        this.eventProposalId = eventProposalId;
        return this;
    }

    public EventProposalCreatedEventBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EventProposalCreatedEventBuilder withDescription(EventDescription description) {
        this.description = description;
        return this;
    }

    public EventProposalCreatedEventBuilder withRequiredAcceptanceThreshold(int requiredAcceptanceThreshold) {
        this.requiredAcceptanceThreshold = requiredAcceptanceThreshold;
        return this;
    }

    public EventProposalCreatedEvent build() {
        return new EventProposalCreatedEvent(
                eventProposalId, name, description, requiredAcceptanceThreshold
        );
    }
}
