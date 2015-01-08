package agh.bit.eventsbc.domain.eventproposal.events;

import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.MemberId;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 08.01.15.
 */

@Value
@Accessors(fluent = true)
public class MemberAlreadyInterestedInEventProposal {

    private final EventProposalId eventProposalId;
    private final MemberId memberId;
    private final String firstName;
    private final String lastName;
    private final String email;

}