package agh.bit.eventsbc.infrastructure;

import agh.bit.eventsbc.domain.eventproposal.factories.TodoItemIdProvider;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by novy on 06.01.15.
 */
public class TodoItemIdProviderImpl implements TodoItemIdProvider {

    private static final String ERROR_MESSAGE = "Requested id batch of negative size";

    @Override
    public TodoItemId nextId() {
        return TodoItemId.of(randomUUIDString());
    }

    @Override
    public List<TodoItemId> nextIdBatch(int batchSize) {
        Preconditions.checkArgument(batchSize >= 0, ERROR_MESSAGE);

        return IntStream
                .range(0, batchSize)
                .mapToObj(i -> TodoItemId.of(randomUUIDString()))
                .collect(Collectors.toList());
    }

    private String randomUUIDString() {
        return UUID.randomUUID().toString();
    }
}
