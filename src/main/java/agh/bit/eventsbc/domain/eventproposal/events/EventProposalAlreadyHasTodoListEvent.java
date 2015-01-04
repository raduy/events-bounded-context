package agh.bit.eventsbc.domain.eventproposal.events;

import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class EventProposalAlreadyHasTodoListEvent {

    private final EventProposalId eventProposalId;
}
