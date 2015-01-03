package agh.bit.eventsbc.domain.todolist.valueobjects;

import lombok.Value;

/**
 * Created by novy handle 03.01.15.
 */

@Value(staticConstructor = "of")
public class TodoListId {

    private final String id;

}
