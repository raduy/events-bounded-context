package agh.bit.eventsbc.domain.eventproposalrequest.commands;

import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class CreateEventProposalRequestCommand {
    private final EventProposalRequestId eventProposalRequestId;
    private final String name;
    private final String description;
    private final String author;
}
