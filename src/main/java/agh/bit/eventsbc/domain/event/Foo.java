package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.events.FooCreatedEvent;
import agh.bit.eventsbc.domain.event.valueobjects.FooId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Foo extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private FooId id;
    private String description;

    public Foo() {
    }

    public Foo(FooId id, String description) {
        FooCreatedEvent eventPayload = new FooCreatedEvent(id, description);
        apply(eventPayload);
    }

    @EventSourcingHandler
    public void onFooCreatedEvent(FooCreatedEvent event) {
        this.id = event.getId();
        this.description = event.getDescription();
    }
}
