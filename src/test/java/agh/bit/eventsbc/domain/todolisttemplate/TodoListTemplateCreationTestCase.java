package agh.bit.eventsbc.domain.todolisttemplate;

import agh.bit.eventsbc.domain.todolisttemplate.commands.CreateTodoListTemplateCommand;
import agh.bit.eventsbc.domain.todolisttemplate.events.TodoListTemplateCreatedEvent;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by novy on 04.01.15.
 */
public class TodoListTemplateCreationTestCase extends TodoListTemplatePreconfiguredTestCase {

    private final TodoListTemplateId id = TodoListTemplateId.of("id");
    private final List<String> todoItemDescriptions = ImmutableList.of("todotem1", "todoitem2");

    @Test
    public void createTodoListTemplateCommandShouldCreateNewTodoListTemplate() throws Exception {
        fixture.given()
                .when(
                        new CreateTodoListTemplateCommand(id, todoItemDescriptions)
                )
                .expectEvents(
                        new TodoListTemplateCreatedEvent(id, todoItemDescriptions)
                );
    }
}
