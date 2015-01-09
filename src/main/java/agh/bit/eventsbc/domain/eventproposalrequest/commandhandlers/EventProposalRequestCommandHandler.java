package agh.bit.eventsbc.domain.eventproposalrequest.commandhandlers;

import agh.bit.eventsbc.domain.eventproposalrequest.EventProposalRequest;
import agh.bit.eventsbc.domain.eventproposalrequest.commands.CreateEventProposalRequestCommand;
import agh.bit.eventsbc.domain.eventproposalrequest.factories.EventProposalRequestFactory;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;

public class EventProposalRequestCommandHandler {

    private Repository<EventProposalRequest> eventProposalRequestRepository;

    @CommandHandler
    public void handle(CreateEventProposalRequestCommand command) {
        final EventProposalRequest eventProposalRequest = EventProposalRequestFactory.create(
                command.eventProposalRequestId(), command.name(), command.description(), command.author()
        );
        eventProposalRequestRepository.add(eventProposalRequest);
    }

    public void setEventProposalRequestRepository(Repository<EventProposalRequest> eventProposalRequestRepository) {
        this.eventProposalRequestRepository = eventProposalRequestRepository;
    }
}
