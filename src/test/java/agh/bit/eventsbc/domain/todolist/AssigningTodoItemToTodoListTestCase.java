package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.builders.AssignTodoItemToTodoListCommandBuilder;
import agh.bit.eventsbc.domain.todolist.builders.TodoItemAssignedToTodoListEventBuilder;
import agh.bit.eventsbc.domain.todolist.commands.AssignTodoItemToTodoListCommand;
import agh.bit.eventsbc.domain.todolist.events.TodoItemAssignedToTodoListEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoItemNotAssignedToTodoListEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoListCreatedEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by novy on 04.01.15.
 */
public class AssigningTodoItemToTodoListTestCase
        extends TodoListPreconfiguredTestCase {

    private final TodoListId todoListId = new TodoListId();
    private final TodoItemId todoItemId = new TodoItemId();

    private TodoListCreatedEvent todoListCreatedEvent;
    private AssignTodoItemToTodoListCommand assignTodoItemToTodoListCommand;
    private TodoItemAssignedToTodoListEvent todoItemAssignedToTodoListEvent;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        todoListCreatedEvent = new TodoListCreatedEvent(
                todoListId
        );

        assignTodoItemToTodoListCommand = AssignTodoItemToTodoListCommandBuilder
                .newAssignTodoItemToTodoListCommand()
                .todoListId(todoListId)
                .todoItemId(todoItemId)
                .build();

        todoItemAssignedToTodoListEvent = TodoItemAssignedToTodoListEventBuilder
                .newTodoItemAssignedToTodoListEvent()
                .todoListId(todoListId)
                .todoItemId(todoItemId)
                .build();
    }

    @Test
    public void assignTodoItemToTodoListShouldCreateNewTodoItem() throws Exception {
        fixture
                .given(todoListCreatedEvent)
                .when(assignTodoItemToTodoListCommand)
                .expectEvents(todoItemAssignedToTodoListEvent);

    }

    @Test
    public void assigningTodoItemWithSameIdTwiceShouldFail() throws Exception {
        TodoItemNotAssignedToTodoListEvent todoItemNotAssignedToTodoListEvent =
                new TodoItemNotAssignedToTodoListEvent(todoListId, todoItemId);

        fixture
                .given(
                        todoListCreatedEvent,
                        todoItemAssignedToTodoListEvent

                )
                .when(assignTodoItemToTodoListCommand)
                .expectEvents(
                        todoItemNotAssignedToTodoListEvent
                );
    }
}
