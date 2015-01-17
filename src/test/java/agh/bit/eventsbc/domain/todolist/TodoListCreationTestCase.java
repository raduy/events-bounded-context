package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.commands.CreateTodoListCommand;
import agh.bit.eventsbc.domain.todolist.events.TodoListCreatedEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.junit.Test;

/**
 * Created by novy on 04.01.15.
 */
public class TodoListCreationTestCase extends TodoListPreconfiguredTestCase {

    @Test
    public void createEventProposalCommandShouldCreateNewEventProposal() throws Exception {
        TodoListId todoListId = new TodoListId();
        fixture.given()
                .when(
                        new CreateTodoListCommand(
                                todoListId
                        )
                )
                .expectEvents(
                        new TodoListCreatedEvent(
                                todoListId
                        )
                );
    }
}
