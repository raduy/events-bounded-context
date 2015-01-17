package agh.bit.eventsbc.domain.todolist.valueobjects;

import agh.bit.eventsbc.domain.common.UUIDBasedId;

/**
 * Created by novy handle 03.01.15.
 */

public class TodoListId extends UUIDBasedId {

    public TodoListId() {
    }

    public TodoListId(String id) {
        super(id);
    }
}

