package agh.bit.eventsbc.domain.eventproposal.factories;

import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;

import java.util.List;

/**
 * Created by novy on 06.01.15.
 */
public interface TodoItemIdProvider {

    TodoItemId nextId();
    List<TodoItemId> nextIdBatch(int batchSize);
}
