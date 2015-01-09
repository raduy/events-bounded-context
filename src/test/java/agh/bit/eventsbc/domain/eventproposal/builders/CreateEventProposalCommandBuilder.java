package agh.bit.eventsbc.domain.eventproposal.builders;

/**
 * Created by novy on 09.01.15.
 */

import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by novy on 09.01.15.
 */

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "newCreateEventProposalCommand")
public class CreateEventProposalCommandBuilder {

    private EventProposalId eventProposalId = EventProposalId.of("default ID");
    private String name = "default name";
    private EventDescription description = EventDescription.of("default description");
    private int requiredAcceptanceThreshold = 15;

    public CreateEventProposalCommand build() {
        return new CreateEventProposalCommand(
                eventProposalId, name, description, requiredAcceptanceThreshold
        );
    }
}

