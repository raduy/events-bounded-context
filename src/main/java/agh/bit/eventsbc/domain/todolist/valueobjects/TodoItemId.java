package agh.bit.eventsbc.domain.todolist.valueobjects;

import agh.bit.eventsbc.domain.common.DomainIdentifier;
import lombok.Value;

/**
 * Created by novy on 03.01.15.
 */

@Value(staticConstructor = "of")
public class TodoItemId implements DomainIdentifier {

    private final String id;
}
