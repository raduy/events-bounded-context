package agh.bit.eventsbc.domain.eventproposal.valueobject;

import lombok.Value;

/**
 * Created by novy handle 03.01.15.
 */

@Value(staticConstructor = "of")
public class EventProposalId {

    private final String id;

}
