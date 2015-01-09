package agh.bit.eventsbc.domain.todolist.commands;

import agh.bit.eventsbc.domain.eventproposal.factories.TodoItemIdProvider;
import agh.bit.eventsbc.domain.todolist.TodoList;
import agh.bit.eventsbc.domain.todolist.commandhandlers.FulfillTodoListWithTemplateCommand;
import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by novy on 06.01.15.
 */

@Component
public class FulfillingTodoListWithTemplateCommandHandler {

    private Repository<TodoList> todoListRepository;
    private Repository<TodoListTemplate> todoListTemplateRepository;
    private TodoItemIdProvider todoItemIdProvider;

    @CommandHandler
    private void handle(FulfillTodoListWithTemplateCommand command) {
        final TodoList todoList = todoListRepository.load(command.todoListId());
        final TodoListTemplate todoListTemplate = todoListTemplateRepository.load(command.todoListTemplateId());

        todoList.fulfillWith(todoListTemplate, command.creationDate(), todoItemIdProvider);
    }


    @Autowired
    @Qualifier("todoListRepository")
    public void setTodoListRepository(Repository<TodoList> todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Autowired
    @Qualifier("todoListTemplateRepository")
    public void setTodoListTemplateRepository(Repository<TodoListTemplate> todoListTemplateRepository) {
        this.todoListTemplateRepository = todoListTemplateRepository;
    }

    @Autowired
    public void setTodoItemIdProvider(TodoItemIdProvider todoItemIdProvider) {
        this.todoItemIdProvider = todoItemIdProvider;
    }
}
