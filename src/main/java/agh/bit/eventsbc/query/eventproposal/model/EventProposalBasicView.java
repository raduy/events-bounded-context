package agh.bit.eventsbc.query.eventproposal.model;

import agh.bit.eventsbc.query.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EventProposalBasicView extends AbstractEntity {

    @Column
    private String name;
    @Column
    private int minimalInterestThreshold;
    @Column
    private String eventProposalId;

}
