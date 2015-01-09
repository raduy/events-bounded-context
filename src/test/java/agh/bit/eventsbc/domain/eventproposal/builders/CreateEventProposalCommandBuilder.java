package agh.bit.eventsbc.domain.eventproposal.builders;

/**
 * Created by novy on 09.01.15.
 */

import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;

/**
 * Created by novy on 09.01.15.
 */

public class CreateEventProposalCommandBuilder {

    private EventProposalId eventProposalId = EventProposalId.of("default ID");
    private String name = "default name";
    private EventDescription description = EventDescription.of("default description");
    private int requiredAcceptanceThreshold = 15;

    private CreateEventProposalCommandBuilder() {}

    public static CreateEventProposalCommandBuilder newCreateEventProposalCommand() {
        return new CreateEventProposalCommandBuilder();
    }

    public CreateEventProposalCommandBuilder withEventProposalId(EventProposalId eventProposalId) {
        this.eventProposalId = eventProposalId;
        return this;
    }

    public CreateEventProposalCommandBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CreateEventProposalCommandBuilder withDescription(EventDescription description) {
        this.description = description;
        return this;
    }

    public CreateEventProposalCommandBuilder withRequiredAcceptanceThreshold(int requiredAcceptanceThreshold) {
        this.requiredAcceptanceThreshold = requiredAcceptanceThreshold;
        return this;
    }

    public CreateEventProposalCommand build() {
        return new CreateEventProposalCommand(
                eventProposalId, name, description, requiredAcceptanceThreshold
        );
    }
}

