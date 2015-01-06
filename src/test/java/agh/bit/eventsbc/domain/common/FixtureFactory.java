package agh.bit.eventsbc.domain.common;

import agh.bit.eventsbc.domain.eventproposal.EventProposal;
import agh.bit.eventsbc.domain.eventproposal.commandhandlers.EventProposalCommandHandler;
import agh.bit.eventsbc.domain.todolist.TodoList;
import agh.bit.eventsbc.domain.todolist.commandhandlers.TodoListCommandHandler;
import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import agh.bit.eventsbc.domain.todolisttemplate.commandhandlers.TodoListTemplateCommandHandler;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;

/**
 * Created by novy on 04.01.15.
 */
public class FixtureFactory {

    // todo: DRY
    public static FixtureConfiguration onEventProposalPreconfiguredFixture() {
        FixtureConfiguration fixture = Fixtures.newGivenWhenThenFixture(EventProposal.class);
        EventProposalCommandHandler commandHandler = new EventProposalCommandHandler();
        commandHandler.setEventProposalRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
        return fixture;
    }

    public static FixtureConfiguration onTodoListPreconfiguredFixture() {
        FixtureConfiguration fixture = Fixtures.newGivenWhenThenFixture(TodoList.class);
        TodoListCommandHandler commandHandler = new TodoListCommandHandler();
        commandHandler.setTodoListRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
        return fixture;
    }

    public static FixtureConfiguration onTodoListTemplatePreconfiguredFixture() {
        FixtureConfiguration fixture = Fixtures.newGivenWhenThenFixture(TodoListTemplate.class);
        TodoListTemplateCommandHandler commandHandler = new TodoListTemplateCommandHandler();
        commandHandler.setTodoListTemplateRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
        return fixture;
    }
}
