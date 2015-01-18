package agh.bit.eventsbc.web.controller;

import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.query.eventproposal.forms.CreateEventProposalForm;
import agh.bit.eventsbc.query.eventproposal.model.EventProposalBasicView;
import agh.bit.eventsbc.query.eventproposal.repositories.EventProposalJpaRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/eventproposal")
public class EventProposalController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private EventProposalJpaRepository eventProposalRepository;

    //TODO: Dozer?
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventProposal(@RequestBody CreateEventProposalForm form) {
        CreateEventProposalCommand command = new CreateEventProposalCommand(
                new EventProposalId(),
                form.getName(),
                EventDescription.of(form.getDescription()),
                form.getMinimalInterestThreshold());
        commandGateway.send(command);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<EventProposalBasicView> loadEventProposal() {
        return eventProposalRepository.findAll();
    }
}
