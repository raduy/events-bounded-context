package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.eventproposal.builders.EventProposalCreatedEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.MemberAlreadyInterestedInEventProposalEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.MemberSignedInterestEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.SignInterestCommandBuilder;
import agh.bit.eventsbc.domain.eventproposal.commands.SignInterestCommand;
import agh.bit.eventsbc.domain.eventproposal.events.AttendeeSignedInterestEvent;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by novy on 08.01.15.
 */
public class SigningInterestTestCase extends EventProposalPreconfiguredTestCase {

    private final EventProposalId eventProposalId = EventProposalId.of("132");
    private final int interestThreshold = 2;

    private final AttendeeId firstMemberId = new AttendeeId();
    private final String firstMemberFirstName = "Eric";
    private final String firstMemberLastName = "Evans";
    private final String firstMemberEmail = "eric.evans@gmail.com";

    private EventProposalCreatedEvent eventProposalCreatedEvent;
    private SignInterestCommand signInterestForFirstMemberCommand;
    private AttendeeSignedInterestEvent firstAttendeeSignedInterestEvent;

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
                .attendeeId(firstMemberId)
                .firstName(firstMemberFirstName)
                .lastName(firstMemberLastName)
                .email(firstMemberEmail)
                .build();

        firstAttendeeSignedInterestEvent = MemberSignedInterestEventBuilder
                .newSignedInterestEvent()
                .eventProposalId(eventProposalId)
                .attendeeId(firstMemberId)
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
                .expectEvents(firstAttendeeSignedInterestEvent);
    }

    @Test
    public void oneShouldNotBeAbleToSignInterestMoreThanOnce() throws Exception {
        fixture
                .given(
                        eventProposalCreatedEvent,
                        firstAttendeeSignedInterestEvent
                )
                .when(signInterestForFirstMemberCommand)
                .expectEvents(
                        MemberAlreadyInterestedInEventProposalEventBuilder
                                .newMemberAlreadyInterestedInEventProposalEvent()
                                .eventProposalId(eventProposalId)
                                .attendeeId(firstMemberId)
                                .firstName(firstMemberFirstName)
                                .lastName(firstMemberLastName)
                                .email(firstMemberEmail)
                                .build()
                );
    }
}
