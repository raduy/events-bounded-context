package agh.bit.eventsbc.web.eventproposal.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: add @NotNull
@Data
@NoArgsConstructor
public class CreateEventProposalForm {

    private String name;
    private String description;
    private int minimalInterestThreshold;

}
