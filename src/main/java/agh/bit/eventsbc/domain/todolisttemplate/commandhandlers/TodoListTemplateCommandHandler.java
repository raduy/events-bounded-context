package agh.bit.eventsbc.domain.todolisttemplate.commandhandlers;

import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import agh.bit.eventsbc.domain.todolisttemplate.commands.CreateTodoListTemplateCommand;
import agh.bit.eventsbc.domain.todolisttemplate.factories.TodoListTemplateFactory;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by novy on 06.01.15.
 */
@Component
public class TodoListTemplateCommandHandler {

    private Repository<TodoListTemplate> todoListTemplateRepository;

    @CommandHandler
    public void handle(CreateTodoListTemplateCommand command) {
        final TodoListTemplate todoListTemplate = TodoListTemplateFactory
                .create(command.id(), command.todoItemDescriptions());
        todoListTemplateRepository.add(todoListTemplate);
    }

    @Autowired
    @Qualifier("todoListTemplateRepository")
    public void setTodoListTemplateRepository(Repository<TodoListTemplate> todoListTemplateRepository) {
        this.todoListTemplateRepository = todoListTemplateRepository;
    }
}
