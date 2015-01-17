package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.builders.TodoItemAssignedToTodoListEventBuilder;
import agh.bit.eventsbc.domain.todolist.commands.MarkTodoItemDoneCommand;
import agh.bit.eventsbc.domain.todolist.events.TodoItemAssignedToTodoListEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoItemMarkedDoneEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoItemNotMarkedDoneEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoListCreatedEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by novy on 04.01.15.
 */
public class MarkingTodoDoneTestCase extends TodoListPreconfiguredTestCase {

    private final TodoListId todoListId = new TodoListId();
    private final TodoItemId todoItemId = new TodoItemId();

    private TodoListCreatedEvent todoListCreatedEvent;
    private TodoItemAssignedToTodoListEvent todoItemAssignedToTodoListEvent;
    private MarkTodoItemDoneCommand markTodoItemDoneCommand;
    private TodoItemMarkedDoneEvent todoItemMarkedDoneEvent;
    private TodoItemNotMarkedDoneEvent todoItemNotMarkedDoneEvent;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        todoListCreatedEvent = new TodoListCreatedEvent(todoListId);

        todoItemAssignedToTodoListEvent = TodoItemAssignedToTodoListEventBuilder
                .newTodoItemAssignedToTodoListEvent()
                .todoListId(todoListId)
                .todoItemId(todoItemId)
                .build();

        markTodoItemDoneCommand = new MarkTodoItemDoneCommand(
                todoListId, todoItemId
        );

        todoItemMarkedDoneEvent = new TodoItemMarkedDoneEvent(
                todoListId, todoItemId
        );

        todoItemNotMarkedDoneEvent = new TodoItemNotMarkedDoneEvent(
                todoListId, todoItemId
        );

    }

    @Test
    public void markTodoDoneCommandShouldProduceCorrespondingEvent() throws Exception {
        fixture
                .given(
                        todoListCreatedEvent,
                        todoItemAssignedToTodoListEvent
                )
                .when(markTodoItemDoneCommand)
                .expectEvents(todoItemMarkedDoneEvent);
    }

    @Test
    public void shouldNotBePossibleToMarkItemDoneTwice() throws Exception {
        fixture
                .given(
                        todoListCreatedEvent,
                        todoItemAssignedToTodoListEvent,
                        todoItemMarkedDoneEvent
                )
                .when(markTodoItemDoneCommand)
                .expectEvents(todoItemNotMarkedDoneEvent);
    }
}
