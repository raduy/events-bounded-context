package agh.bit.eventsbc.domain.event.valueobjects;

import agh.bit.eventsbc.domain.common.UUIDBasedId;

public class EventId extends UUIDBasedId {

    public EventId() {
    }

    public EventId(String id) {
        super(id);
    }
}
