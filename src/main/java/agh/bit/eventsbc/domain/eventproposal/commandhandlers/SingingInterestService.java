package agh.bit.eventsbc.domain.eventproposal.commandhandlers;

import agh.bit.eventsbc.domain.eventproposal.EventProposal;
import agh.bit.eventsbc.domain.eventproposal.commands.SignInterestCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by novy on 08.01.15.
 */

//todo: should this be named this way?

@Component
public class SingingInterestService {

    private Repository<EventProposal> eventProposalRepository;

    @CommandHandler
    public void handle(SignInterestCommand command) {
        final EventProposal eventProposal = eventProposalRepository.load(command.eventProposalId());

        eventProposal.registerInterestedStudent(
                command.memberId(), command.firstName(), command.lastName(), command.email()
        );
    }

    @Autowired
    @Qualifier("eventProposalRepository")
    public void setEventProposalRepository(Repository<EventProposal> eventProposalRepository) {
        this.eventProposalRepository = eventProposalRepository;
    }

}
