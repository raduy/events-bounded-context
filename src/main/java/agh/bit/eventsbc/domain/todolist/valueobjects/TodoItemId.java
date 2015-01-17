package agh.bit.eventsbc.domain.todolist.valueobjects;

import agh.bit.eventsbc.domain.common.UUIDBasedId;

/**
 * Created by novy on 03.01.15.
 */
public class TodoItemId extends UUIDBasedId {

    public TodoItemId() {
    }

    public TodoItemId(String id) {
        super(id);
    }
}
