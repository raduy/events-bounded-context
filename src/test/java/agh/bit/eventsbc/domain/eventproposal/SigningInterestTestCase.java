package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.commands.SignInterestCommand;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.events.MemberAlreadyInterestedInEventProposal;
import agh.bit.eventsbc.domain.eventproposal.events.MemberSignedInterestEvent;
import agh.bit.eventsbc.domain.eventproposal.events.MinimalInterestedSatisfiedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.MemberId;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by novy on 08.01.15.
 */
public class SigningInterestTestCase extends EventProposalPreconfiguredTestCase {

    final int interestThreshold = 2;
    private final EventProposalId eventProposalId = EventProposalId.of("132");

    final MemberId firstMemberId = MemberId.of("666");
    final String firstMemberFirstName = "Eric";
    final String firstMemberLastName = "Evans";
    final String firstMemberEmail = "eric.evans@gmail.com";

    private EventProposalCreatedEvent eventProposalCreatedEvent;
    private SignInterestCommand signInterestForFirstMemberCommand;
    private MemberSignedInterestEvent firstMemberSignedInterestEvent;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        eventProposalCreatedEvent = new EventProposalCreatedEvent(
                eventProposalId,
                "event",
                EventDescription.of("desc"),
                interestThreshold
        );

        signInterestForFirstMemberCommand = new SignInterestCommand(
                eventProposalId,
                firstMemberId,
                firstMemberFirstName,
                firstMemberLastName,
                firstMemberEmail
        );

        firstMemberSignedInterestEvent = new MemberSignedInterestEvent(
                eventProposalId,
                firstMemberId,
                firstMemberFirstName,
                firstMemberLastName,
                firstMemberEmail
        );
    }

    @Test
    public void shouldSignInterestCommandProduceCorrespondingEvent() throws Exception {
        fixture
                .given(eventProposalCreatedEvent)
                .when(signInterestForFirstMemberCommand)
                .expectEvents(firstMemberSignedInterestEvent);
    }

    @Test
    public void oneShouldNotBeAbleToSignInterestMoreThanOnce() throws Exception {
        fixture
                .given(
                        eventProposalCreatedEvent,
                        firstMemberSignedInterestEvent
                )
                .when(signInterestForFirstMemberCommand)
                .expectEvents(
                        new MemberAlreadyInterestedInEventProposal(
                                eventProposalId,
                                firstMemberId,
                                firstMemberFirstName,
                                firstMemberLastName,
                                firstMemberEmail
                        )
                );
    }
}
