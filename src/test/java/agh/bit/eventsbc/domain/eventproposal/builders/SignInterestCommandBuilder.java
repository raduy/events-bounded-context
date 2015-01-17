package agh.bit.eventsbc.domain.eventproposal.builders;

import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.eventproposal.commands.SignInterestCommand;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by novy on 10.01.15.
 */

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "newSignInterestCommand")
public class SignInterestCommandBuilder {

    private EventProposalId eventProposalId = new EventProposalId();
    private AttendeeId attendeeId = new AttendeeId();
    private String firstName = "default firs name";
    private String lastName = "default last name";
    private String email = "default.email@gmail.com";

    public SignInterestCommand build() {
        return new SignInterestCommand(
                eventProposalId,
                attendeeId,
                firstName,
                lastName,
                email
        );
    }
}
