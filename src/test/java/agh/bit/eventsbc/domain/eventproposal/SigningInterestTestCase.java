package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.builders.EventProposalCreatedEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.MemberAlreadyInterestedInEventProposalEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.MemberSignedInterestEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.SignInterestCommandBuilder;
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

    private final EventProposalId eventProposalId = EventProposalId.of("132");
    private final int interestThreshold = 2;

    private final MemberId firstMemberId = MemberId.of("666");
    private final String firstMemberFirstName = "Eric";
    private final String firstMemberLastName = "Evans";
    private final String firstMemberEmail = "eric.evans@gmail.com";

    private EventProposalCreatedEvent eventProposalCreatedEvent;
    private SignInterestCommand signInterestForFirstMemberCommand;
    private MemberSignedInterestEvent firstMemberSignedInterestEvent;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        eventProposalCreatedEvent = EventProposalCreatedEventBuilder
                .newEventProposalCreatedEvent()
                .eventProposalId(eventProposalId)
                .minimalInterestThreshold(interestThreshold)
                .build();

        signInterestForFirstMemberCommand = SignInterestCommandBuilder
                .newSignInterestCommand()
                .eventProposalId(eventProposalId)
                .memberId(firstMemberId)
                .firstName(firstMemberFirstName)
                .lastName(firstMemberLastName)
                .email(firstMemberEmail)
                .build();

        firstMemberSignedInterestEvent = MemberSignedInterestEventBuilder
                .newSignedInterestEvent()
                .eventProposalId(eventProposalId)
                .memberId(firstMemberId)
                .firstName(firstMemberFirstName)
                .lastName(firstMemberLastName)
                .email(firstMemberEmail)
                .build();
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
                        MemberAlreadyInterestedInEventProposalEventBuilder
                                .newMemberAlreadyInterestedInEventProposalEvent()
                                .eventProposalId(eventProposalId)
                                .memberId(firstMemberId)
                                .firstName(firstMemberFirstName)
                                .lastName(firstMemberLastName)
                                .email(firstMemberEmail)
                                .build()
                );
    }
}
