package agh.bit.eventsbc.domain.commands;

import agh.bit.eventsbc.domain.valueobject.FooId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CreateFooCommand {
    @TargetAggregateIdentifier
    private final FooId id;
    private final String description;

    public CreateFooCommand(FooId id, String description) {
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
