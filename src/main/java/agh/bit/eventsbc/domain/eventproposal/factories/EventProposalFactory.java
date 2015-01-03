package agh.bit.eventsbc.domain.eventproposal.factories;

import agh.bit.eventsbc.domain.eventproposal.aggregates.EventProposal;
import agh.bit.eventsbc.domain.eventproposal.valueobject.EventProposalId;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposalFactory  {

    public static EventProposal create(EventProposalId id, String name) {
        return new EventProposal(id, name);

    }
}
