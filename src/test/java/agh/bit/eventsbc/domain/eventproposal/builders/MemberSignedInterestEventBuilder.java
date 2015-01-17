package agh.bit.eventsbc.domain.eventproposal.builders;

import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.eventproposal.events.AttendeeSignedInterestEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by novy on 09.01.15.
 */

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "newSignedInterestEvent")
public class MemberSignedInterestEventBuilder {

    private EventProposalId eventProposalId = EventProposalId.of("123");
    private AttendeeId attendeeId = new AttendeeId();

    private String firstName = "default first name";
    private String lastName = "default last name";
    private String email = "default.email@gmail.com";

    public AttendeeSignedInterestEvent build() {
        return new AttendeeSignedInterestEvent(eventProposalId, attendeeId, firstName, lastName, email);
    }
}
