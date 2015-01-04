package agh.bit.eventsbc.domain.todolist.commandhandlers;

import agh.bit.eventsbc.domain.todolist.TodoList;
import agh.bit.eventsbc.domain.todolist.commands.AssignTodoItemToTodoListCommand;
import agh.bit.eventsbc.domain.todolist.commands.CreateTodoListCommand;
import agh.bit.eventsbc.domain.todolist.factories.TodoListFactory;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by novy on 04.01.15.
 */

@Component
public class TodoListCommandHandler {

    private Repository<TodoList> todoListRepository;

    @CommandHandler
    public void handle(CreateTodoListCommand command) {
        final TodoList todoList = TodoListFactory.create(command.todoListId());
        todoListRepository.add(todoList);
    }

    @CommandHandler
    public void handle(AssignTodoItemToTodoListCommand command) {
        final TodoList todoList = todoListRepository.load(command.todoListId());
        todoList.assignTodoItem(
                command.todoItemId(),
                command.title(),
                command.content(),
                command.createdAt()
        );
    }


    @Autowired
    @Qualifier("todoListRepository")
    public void setTodoListRepository(Repository<TodoList> todoListRepository) {
        this.todoListRepository = todoListRepository;
    }
}
