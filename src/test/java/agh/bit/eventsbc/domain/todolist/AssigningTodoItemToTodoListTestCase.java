package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.commands.AssignTodoItemToTodoListCommand;
import agh.bit.eventsbc.domain.todolist.events.TodoItemAssignedToTodoListEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoItemNotAssignedToTodoList;
import agh.bit.eventsbc.domain.todolist.events.TodoListCreatedEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by novy on 04.01.15.
 */
public class AssigningTodoItemToTodoListTestCase
        extends TodoListPreconfiguredTestCase {

    private final TodoListId todoListId = TodoListId.of("dummy id");


    @Test
    public void assignTodoItemToTodoListShouldCreateNewTodoItem() throws Exception {
        final TodoItemId todoItemId = TodoItemId.of("123");
        final String content = "content";
        final LocalDate now = LocalDate.now();

        fixture
                .given(
                        new TodoListCreatedEvent(
                                todoListId
                        )
                )
                .when(
                        new AssignTodoItemToTodoListCommand(
                                todoListId, todoItemId,
                                content, now
                        )
                )
                .expectEvents(
                        new TodoItemAssignedToTodoListEvent(
                                todoListId, todoItemId,
                                content, now
                        )
                );

    }

    @Test
    public void assigningTodoItemWithSameIdTwiceShouldFail() throws Exception {
        final TodoItemId todoItemId = TodoItemId.of("123");
        final String content = "content";
        final LocalDate now = LocalDate.now();

        fixture
                .given(
                        new TodoListCreatedEvent(
                                todoListId
                        ),
                        new TodoItemAssignedToTodoListEvent(
                                todoListId, todoItemId,
                                 content, now
                        )
                )
                .when(
                        new AssignTodoItemToTodoListCommand(
                                todoListId, todoItemId,
                                "different content",
                                LocalDate.now()
                        )
                )
                .expectEvents(
                        new TodoItemNotAssignedToTodoList(
                                todoListId, todoItemId
                        )
                );

    }
}
