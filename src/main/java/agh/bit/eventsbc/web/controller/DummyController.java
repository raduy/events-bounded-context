package agh.bit.eventsbc.web.controller;

import agh.bit.eventsbc.domain.eventproposal.EventProposal;
import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    @Qualifier("eventProposalRepository")
    private Repository<EventProposal> eventProposalRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public DummyClass getDummyClass() {
        return new DummyClass("Tom", "Smith");
    }

    // get should be replaced with post, command send as RequestBody form, http status - 201
    @RequestMapping(value = "/eventproposal", method = RequestMethod.GET)
    public void createEventProposal() {
        CreateEventProposalCommand command = new CreateEventProposalCommand(
                new EventProposalId("1"),
                "Test",
                EventDescription.of("test desc"),
                12);
        commandGateway.send(command);
    }

    // we need query model :) below - hack
    @RequestMapping(value = "/eventproposal/{id}", method = RequestMethod.GET)
    public EventProposalId loadEventProposal(@PathVariable("id") String id) {
        UnitOfWork uow = DefaultUnitOfWork.startAndGet();
        EventProposal eventProposal = eventProposalRepository.load(new EventProposalId(id));
        EventProposalId proposalId = (EventProposalId) eventProposal.getIdentifier();
        uow.commit();
        return proposalId;
    }
}
