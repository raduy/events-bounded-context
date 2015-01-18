package agh.bit.eventsbc.query.eventproposal.model;

import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.query.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EventProposalBasicView extends AbstractEntity {
    @Column
    private String name;

    @Column
    private int minimalInterestThreshold;

    @Column
    private String eventProposalId;

    protected EventProposalBasicView() {
    }

    // should we make EventProposalId equivalent("persistable"), or leave it?
    public EventProposalBasicView(EventProposalCreatedEvent event) {
        this.eventProposalId = event.eventProposalId().getId();
        this.name = event.name();
        this.minimalInterestThreshold = event.minimalInterestThreshold();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinimalInterestThreshold() {
        return minimalInterestThreshold;
    }

    public void setMinimalInterestThreshold(int minimalInterestThreshold) {
        this.minimalInterestThreshold = minimalInterestThreshold;
    }

    public String getEventProposalId() {
        return eventProposalId;
    }

    public void setEventProposalId(String eventProposalId) {
        this.eventProposalId = eventProposalId;
    }
}
