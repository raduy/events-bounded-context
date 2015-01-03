package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.todolist.entities.TodoItem;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import java.util.Collection;


/**
 * Created by novy handle 03.01.15.
 */
public class TodoList {

    @EventSourcedMember
    private Collection<TodoItem> todoItems = Lists.newArrayList();
}
