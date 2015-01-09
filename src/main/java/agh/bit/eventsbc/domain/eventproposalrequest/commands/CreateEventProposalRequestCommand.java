package agh.bit.eventsbc.domain.eventproposalrequest.commands;

import agh.bit.eventsbc.domain.eventproposalrequest.valueobjects.EventProposalRequestId;
import lombok.Value;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

@Value
@Accessors(fluent = true)
public class CreateEventProposalRequestCommand {

    @TargetAggregateIdentifier
    private final EventProposalRequestId eventProposalRequestId;
    private final String name;
    private final String description;
    private final String author;
}
