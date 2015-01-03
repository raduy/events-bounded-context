package agh.bit.eventsbc.domain.eventproposal.commandhandlers;

import agh.bit.eventsbc.domain.eventproposal.aggregates.EventProposal;
import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.factories.EventProposalFactory;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by novy handle 03.01.15.
 */

@Component
public class EventProposalCommandHandler {

    private Repository<EventProposal> eventProposalRepository;

    @CommandHandler
    public void handle(CreateEventProposalCommand command) {
        final EventProposal eventProposal = EventProposalFactory.create(command.eventProposalId(), command.name(), command.description());
        eventProposalRepository.add(eventProposal);
    }

    @Autowired
    @Qualifier("eventProposalRepository")
    public void setEventProposalRepository(Repository<EventProposal> eventProposalRepository) {
        this.eventProposalRepository = eventProposalRepository;
    }
}


