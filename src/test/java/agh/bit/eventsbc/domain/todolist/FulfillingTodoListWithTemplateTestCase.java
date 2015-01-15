package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.eventproposal.factories.TodoItemIdProvider;
import agh.bit.eventsbc.domain.todolist.builders.TemplateBuilder;
import agh.bit.eventsbc.domain.todolist.commandhandlers.FulfillTodoListWithTemplateCommand;
import agh.bit.eventsbc.domain.todolist.commands.FulfillingTodoListWithTemplateCommandHandler;
import agh.bit.eventsbc.domain.todolist.events.TodoItemAssignedToTodoListEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoListCreatedEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoListFulfilledWithTemplateEvent;
import agh.bit.eventsbc.domain.todolist.events.TodoListNotFulfilledWithTemplateEvent;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import com.google.common.collect.ImmutableList;
import org.axonframework.repository.Repository;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by novy on 06.01.15.
 */
public class FulfillingTodoListWithTemplateTestCase {

    private FixtureConfiguration fixture;
    private FulfillingTodoListWithTemplateCommandHandler commandHandler;

    private final TodoListTemplateId todoListTemplateId = TodoListTemplateId.of("id");
    private final List<TodoItemId> todoItemIds = ImmutableList.of(
            TodoItemId.of("123"),
            TodoItemId.of("321")
    );
    private final String[] todoItemDescriptions = new String[]{"todoitem1", "todoitem2"};

    private final TodoListId todoListId = TodoListId.of("id");
    private final LocalDate now = LocalDate.now();

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(TodoList.class);

        commandHandler = new FulfillingTodoListWithTemplateCommandHandler();

        Repository<TodoListTemplate> todoListTemplateRepositoryMock =
                prepareTodoListTemplateRepositoryMock();

        commandHandler.setTodoListRepository(fixture.getRepository());
        commandHandler.setTodoListTemplateRepository(todoListTemplateRepositoryMock);

        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void shouldTodoItemListBeFilledGivenTodoListWithoutAnyTodoItemAssigned() throws Exception {
        commandHandler.setTodoItemIdProvider(mockedTodoItemIdProvider());

        fixture
                .given(
                        new TodoListCreatedEvent(todoListId)
                )
                .when(
                        new FulfillTodoListWithTemplateCommand(
                                todoListId,
                                todoListTemplateId,
                                now
                        )
                )
                .expectEvents(
                        new TodoListFulfilledWithTemplateEvent(
                                todoListId,
                                todoListTemplateId,
                                todoItemIds,
                                Arrays.asList(todoItemDescriptions),
                                now
                        )
                );

    }


    @Test
    public void shouldNotTodoItemBeFilledGivenNonEmptyTodoList() throws Exception {
        final TodoListId todoListId = TodoListId.of("id");
        final LocalDate now = LocalDate.now();

        fixture
                .given(
                        new TodoListCreatedEvent(todoListId),
                        new TodoItemAssignedToTodoListEvent(
                                todoListId,
                                TodoItemId.of("id"),
                                "Todo Item",
                                LocalDate.of(2010, 5, 5)
                        )
                )
                .when(
                        new FulfillTodoListWithTemplateCommand(
                                todoListId,
                                todoListTemplateId,
                                now
                        )
                )
                .expectEvents(
                        new TodoListNotFulfilledWithTemplateEvent(
                                todoListId,
                                todoListTemplateId
                        )
                );

    }

    private Repository<TodoListTemplate> prepareTodoListTemplateRepositoryMock() {
        final TodoListTemplate template = TemplateBuilder.builder()
                .withId(todoListTemplateId)
                .withDescriptions(todoItemDescriptions)
                .build();

        Repository<TodoListTemplate> repositoryMock = mock(Repository.class);
        given(repositoryMock.load(todoListTemplateId)).willReturn(template);

        return repositoryMock;
    }

    private TodoItemIdProvider mockedTodoItemIdProvider() {
        TodoItemIdProvider idProviderMock = mock(TodoItemIdProvider.class);
        given(idProviderMock.nextIdBatch(todoItemDescriptions.length)).willReturn(todoItemIds);
        return idProviderMock;
    }
}
