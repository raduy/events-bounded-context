package agh.bit.eventsbc.domain.eventproposalrequest.valueobjects;

import agh.bit.eventsbc.domain.common.DomainIdentifier;
import lombok.Value;

@Value(staticConstructor = "of")
public class EventProposalRequestId implements DomainIdentifier {
    private final String id;
}
