package agh.bit.eventsbc.domain.eventproposal.commands;

import agh.bit.eventsbc.domain.eventproposal.valueobject.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobject.EventProposalId;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy handle 03.01.15.
 */

@Value
@Accessors(fluent = true)
public class CreateEventProposalCommand {

    private final EventProposalId eventProposalId;
    private final String name;
    private final EventDescription description;

}
