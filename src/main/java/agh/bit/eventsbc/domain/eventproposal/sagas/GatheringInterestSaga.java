package agh.bit.eventsbc.domain.eventproposal.sagas;

import agh.bit.eventsbc.domain.eventproposal.events.AttendeeSignedInterestEvent;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.events.MinimalInterestedSatisfiedEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.InterestThreshold;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by novy on 09.01.15.
 */

@Component
public class GatheringInterestSaga extends AbstractAnnotatedSaga {

    public static final String EVENT_PROPOSAL_ID_PROPERTY = "eventProposalId";

    private CommandGateway commandGateway;
    private EventScheduler eventScheduler;
    private EventBus eventBus;

    private InterestThreshold interestThreshold;
    private int interestedAttendees = 0;
    private GatheringState gatheringState = GatheringState.PENDING;

    @StartSaga
    @SagaEventHandler(associationProperty = EVENT_PROPOSAL_ID_PROPERTY)
    public void on(EventProposalCreatedEvent event) {
        interestThreshold = InterestThreshold.of(
                event.minimalInterestThreshold()
        );
    }

    @SagaEventHandler(associationProperty = EVENT_PROPOSAL_ID_PROPERTY)
    public void on(AttendeeSignedInterestEvent event) {
        interestedAttendees++;
        if (gatheringState == GatheringState.PENDING && interestThreshold.fulfilledWith(interestedAttendees)) {
            gatheringState = GatheringState.THRESHOLD_FULFILLED;
            eventBus.publish(
                    eventMessageWrapper(
                            new MinimalInterestedSatisfiedEvent(event.eventProposalId())
                    )
            );
        }

    }

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Autowired
    public void setEventScheduler(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
    }

    @Autowired
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    private <T> GenericEventMessage<T> eventMessageWrapper(T eventMessage) {
        return new GenericEventMessage<>(eventMessage);
    }
}
