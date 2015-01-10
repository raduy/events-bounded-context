package agh.bit.eventsbc.domain.eventproposal.builders;

import agh.bit.eventsbc.domain.eventproposal.events.MemberAlreadyInterestedInEventProposal;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.MemberId;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by novy on 10.01.15.
 */

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "newMemberAlreadyInterestedInEventProposalEvent")
public class MemberAlreadyInterestedInEventProposalEventBuilder {

    private EventProposalId eventProposalId = EventProposalId.of("123");
    private MemberId memberId = MemberId.of("321");
    private String firstName = "default firs name";
    private String lastName = "default last name";
    private String email = "default.email@gmail.com";

    public MemberAlreadyInterestedInEventProposal build() {
        return new MemberAlreadyInterestedInEventProposal(
                eventProposalId,
                memberId,
                firstName,
                lastName,
                email
        );
    }

}
