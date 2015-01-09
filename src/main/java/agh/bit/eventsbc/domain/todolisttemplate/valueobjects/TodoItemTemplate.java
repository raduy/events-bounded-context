package agh.bit.eventsbc.domain.todolisttemplate.valueobjects;

import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 05.01.15.
 */

// todo: for now, just description

@Value
@Accessors(fluent = true)
public class TodoItemTemplate {

    private final String description;
}
