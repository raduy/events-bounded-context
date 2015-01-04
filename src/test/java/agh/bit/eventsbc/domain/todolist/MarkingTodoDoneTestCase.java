package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.commands.MarkTodoItemDoneCommand;
import agh.bit.eventsbc.domain.todolist.events.TodoItemMarkedDoneEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoItemAssignedToTodoListEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoItemNotMarkedDoneEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoListCreatedEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by novy on 04.01.15.
 */
public class MarkingTodoDoneTestCase extends TodoListPreconfiguredTestCase {

    private final TodoListId todoListId = TodoListId.of("id");
    private final TodoItemId todoItemId = TodoItemId.of("123");
    private final String itemTitle = "itemTitle";
    private final String itemContent = "itemContent";
    private final LocalDate itemCreationDate = LocalDate.now();

    @Test
    public void markTodoDoneCommandShouldProduceCorrespondingEvent() throws Exception {
        fixture
                .given(
                        new TodoListCreatedEvent(
                                todoListId
                        ),
                        new TodoItemAssignedToTodoListEvent(
                                todoListId, todoItemId,
                                itemTitle, itemContent, itemCreationDate
                        )
                )
                .when(
                        new MarkTodoItemDoneCommand(
                                todoListId, todoItemId
                        )
                )
                .expectEvents(
                        new TodoItemMarkedDoneEvent(
                                todoListId, todoItemId
                        )
                );
    }

    @Test
    public void shouldNotBePossibleToMarkItemDoneTwice() throws Exception {
        fixture
                .given(
                        new TodoListCreatedEvent(
                                todoListId
                        ),
                        new TodoItemAssignedToTodoListEvent(
                                todoListId, todoItemId,
                                itemTitle, itemContent, itemCreationDate
                        ),
                        new TodoItemMarkedDoneEvent(
                                todoListId, todoItemId
                        )
                )
                .when(
                        new MarkTodoItemDoneCommand(
                                todoListId, todoItemId
                        )
                )
                .expectEvents(
                        new TodoItemNotMarkedDoneEvent(
                                todoListId, todoItemId
                        )
                );

    }
}
