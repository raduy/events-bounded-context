package agh.bit.eventsbc.domain.eventproposalrequest.factories;

import agh.bit.eventsbc.domain.eventproposalrequest.EventProposalRequest;
import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;

public class EventProposalRequestFactory {

    public static EventProposalRequest create(EventProposalRequestId id,
                                       String name,
                                       String description,
                                       String author) {

        return new EventProposalRequest(id, name, description, author);

    }

}
