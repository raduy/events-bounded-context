package agh.bit.eventsbc.domain.eventproposal.builders;

import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by novy on 09.01.15.
 */

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "newEventProposalCreatedEvent")
public class EventProposalCreatedEventBuilder {

    private EventProposalId eventProposalId = EventProposalId.of("default ID");
    private String name = "default name";
    private EventDescription description = EventDescription.of("default description");
    private int minimalInterestThreshold = 15;

    public EventProposalCreatedEvent build() {
        return new EventProposalCreatedEvent(
                eventProposalId, name, description, minimalInterestThreshold
        );
    }
}
