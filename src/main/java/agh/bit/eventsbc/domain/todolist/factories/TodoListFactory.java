package agh.bit.eventsbc.domain.todolist.factories;

import agh.bit.eventsbc.domain.todolist.TodoList;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;

/**
 * Created by novy on 04.01.15.
 */
public class TodoListFactory {

    public static TodoList create(TodoListId todoListId) {
        return new TodoList(todoListId);
    }
}
