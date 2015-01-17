package agh.bit.eventsbc.domain.eventproposal.valueobjects;

import agh.bit.eventsbc.domain.common.UUIDBasedId;

/**
 * Created by novy handle 03.01.15.
 */

public class EventProposalId extends UUIDBasedId {

    public EventProposalId() {
    }

    public EventProposalId(String id) {
        super(id);
    }
}
