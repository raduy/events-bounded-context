package agh.bit.eventsbc.domain.todolisttemplate.factories;

import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;

import java.util.List;

/**
 * Created by novy on 06.01.15.
 */
public class TodoListTemplateFactory {

    public static TodoListTemplate create(TodoListTemplateId id, List<String> todoItemDescriptions) {
        return new TodoListTemplate(id, todoItemDescriptions);
    }
}
