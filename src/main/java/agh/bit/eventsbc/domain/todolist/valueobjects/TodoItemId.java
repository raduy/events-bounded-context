package agh.bit.eventsbc.domain.todolist.valueobjects;

import lombok.Value;

/**
 * Created by novy on 03.01.15.
 */

@Value(staticConstructor = "of")
public class TodoItemId {

    private final String id;
}
