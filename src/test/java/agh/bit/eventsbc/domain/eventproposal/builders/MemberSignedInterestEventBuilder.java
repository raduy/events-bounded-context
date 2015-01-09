package agh.bit.eventsbc.domain.eventproposal.builders;

import agh.bit.eventsbc.domain.eventproposal.events.MemberSignedInterestEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.MemberId;
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
    private MemberId memberId = MemberId.of("123");
    private String firstName = "default first name";
    private String lastName = "default last name";
    private String email = "default.email@gmail.com";

    public MemberSignedInterestEvent build() {
        return new MemberSignedInterestEvent(eventProposalId, memberId, firstName, lastName, email);
    }
}
