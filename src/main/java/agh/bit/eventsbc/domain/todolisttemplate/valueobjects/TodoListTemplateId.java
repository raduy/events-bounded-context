package agh.bit.eventsbc.domain.todolisttemplate.valueobjects;

import agh.bit.eventsbc.domain.common.DomainIdentifier;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 05.01.15.
 */

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class TodoListTemplateId implements DomainIdentifier {

    private final String id;
}
