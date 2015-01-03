package agh.bit.eventsbc.domain.eventproposal.commands;

import agh.bit.eventsbc.domain.eventproposal.valueobject.EventProposalId;

/**
 * Created by novy handle 03.01.15.
 */
public class CreateEventProposalCommand {
    private final EventProposalId eventProposalId;
    private final String name;

    public CreateEventProposalCommand(EventProposalId eventProposalId, String name) {
        this.eventProposalId = eventProposalId;
        this.name = name;
    }

    public EventProposalId eventProposalId() {
        return eventProposalId;
    }

    public String name() {
        return name;
    }
}
