package agh.bit.eventsbc.domain.eventproposal.factories;

import agh.bit.eventsbc.domain.eventproposal.EventProposal;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposalFactory {

    public static EventProposal create(EventProposalId id,
                                       String name,
                                       EventDescription description,
                                       int requiredInterestThreshold) {

        return new EventProposal(id, name, description, requiredInterestThreshold);

    }
}
