package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.builders.EventProposalCreatedEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.builders.MemberSignedInterestEventBuilder;
import agh.bit.eventsbc.domain.eventproposal.events.MinimalInterestedSatisfiedEvent;
import agh.bit.eventsbc.domain.eventproposal.sagas.GatheringInterestSaga;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.MemberId;
import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by novy on 09.01.15.
 */
public class GatheringInterestSagaTest {

    private AnnotatedSagaTestFixture fixture;

    private final EventProposalId eventProposalId = EventProposalId.of("123");
    private final int minimalInterestedThreshold = 2;


    @Before
    public void setUp() throws Exception {
        fixture = new AnnotatedSagaTestFixture(GatheringInterestSaga.class);
    }

    @Test
    public void shouldProduceMinimalInterestSatisfiedEventWhenMinimalInterestSatisfied() throws Exception {

        fixture
                .givenAggregate(eventProposalId)
                .published(
                        EventProposalCreatedEventBuilder
                                .newEventProposalCreatedEvent()
                                .eventProposalId(eventProposalId)
                                .minimalInterestThreshold(minimalInterestedThreshold)
                                .build(),

                        MemberSignedInterestEventBuilder
                                .newSignedInterestEvent()
                                .eventProposalId(eventProposalId)
                                .memberId(MemberId.of("123"))
                                .build()
                )
                .whenAggregate(eventProposalId)
                .publishes(
                        MemberSignedInterestEventBuilder
                                .newSignedInterestEvent()
                                .eventProposalId(eventProposalId)
                                .memberId(MemberId.of("321"))
                                .build()
                )
                .expectPublishedEvents(
                        new MinimalInterestedSatisfiedEvent(eventProposalId)
                );
    }

    @Test
    public void shouldNotProduceMinimalInterestSatisfiedEventMoreThanOnce() throws Exception {

        fixture
                .givenAggregate(eventProposalId)
                .published(
                        EventProposalCreatedEventBuilder
                                .newEventProposalCreatedEvent()
                                .eventProposalId(eventProposalId)
                                .minimalInterestThreshold(minimalInterestedThreshold)
                                .build(),

                        MemberSignedInterestEventBuilder
                                .newSignedInterestEvent()
                                .eventProposalId(eventProposalId)
                                .memberId(MemberId.of("123"))
                                .build(),

                        MemberSignedInterestEventBuilder
                                .newSignedInterestEvent()
                                .eventProposalId(eventProposalId)
                                .memberId(MemberId.of("321"))
                                .build()
                )
                .whenAggregate(eventProposalId)
                .publishes(
                        MemberSignedInterestEventBuilder
                                .newSignedInterestEvent()
                                .eventProposalId(eventProposalId)
                                .memberId(MemberId.of("666"))
                                .build()
                )
                .expectPublishedEvents();
    }
}
