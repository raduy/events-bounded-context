package agh.bit.eventsbc.domain.eventproposal.commands;

import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import lombok.Value;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by novy on 04.01.15.
 */

@Value
@Accessors(fluent = true)
public class SignInterestCommand {

    @TargetAggregateIdentifier
    private final EventProposalId eventProposalId;
    private final AttendeeId memberId;
    private final String firstName;
    private final String lastName;
    private final String email;

}
