package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.valueobjects.FooId;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class FooCreatedEvent {
    private final FooId id;
    private final String description;

    public FooCreatedEvent(FooId id, String description) {
        this.id = id;
        this.description = description;
    }

    public FooId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
