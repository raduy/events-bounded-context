package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.entities.TodoItem;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import java.util.Collection;


/**
 * Created by novy handle 03.01.15.
 */
public class TodoList extends AbstractAnnotatedEntity {

    private final TodoListId id;

    @EventSourcedMember
    private Collection<TodoItem> todoItems = Lists.newArrayList();

    public TodoList(TodoListId id) {
        this.id = id;
    }
}
