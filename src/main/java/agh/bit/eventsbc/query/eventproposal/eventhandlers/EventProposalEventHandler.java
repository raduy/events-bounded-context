package agh.bit.eventsbc.query.eventproposal.eventhandlers;

import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.query.eventproposal.model.EventProposalBasicView;
import agh.bit.eventsbc.query.eventproposal.repositories.EventProposalJpaRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventProposalEventHandler {

    @Autowired
    private EventProposalJpaRepository eventProposalRepository;

    @EventHandler
    public void handle(EventProposalCreatedEvent event) {
        eventProposalRepository.save(
                new EventProposalBasicView(
                    event.name(),
                    event.minimalInterestThreshold(),
                    event.eventProposalId().getId()
                ));
    }
}
