package agh.bit.eventsbc.domain.eventproposal.valueobjects;

import agh.bit.eventsbc.domain.common.DomainIdentifier;
import lombok.Value;

/**
 * Created by novy handle 03.01.15.
 */

@Value(staticConstructor = "of")
public class EventProposalId implements DomainIdentifier {

    private final String id;

}
