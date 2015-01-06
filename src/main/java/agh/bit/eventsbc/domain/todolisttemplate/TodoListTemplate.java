package agh.bit.eventsbc.domain.todolisttemplate;

import agh.bit.eventsbc.domain.todolisttemplate.events.TodoListTemplateCreatedEvent;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoItemTemplate;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by novy on 05.01.15.
 */
public class TodoListTemplate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private TodoListTemplateId id;

    private List<TodoItemTemplate> todoItemTemplates = Lists.newArrayList();

    private TodoListTemplate() {}

    public TodoListTemplate(TodoListTemplateId id, List<String> todoItemDescriptions) {
        apply(new TodoListTemplateCreatedEvent(id, todoItemDescriptions));
    }

    @EventSourcingHandler
    public void on(TodoListTemplateCreatedEvent event) {
        this.id = event.id();
        this.todoItemTemplates = itemsFrom(event.todoItemDescriptions());
    }

    // todo: at this point, those methods below create unnecessary complexity, consider just using collection of strings
    private List<TodoItemTemplate> itemsFrom(List<String> descriptions) {
        return descriptions.stream()
                .map(TodoItemTemplate::new)
                .collect(Collectors.toList());

    }

    public List<String> todoItemDescriptions() {
        return todoItemTemplates.stream()
                .map(TodoItemTemplate::description)
                .collect(Collectors.toList());
    }

    public TodoListTemplateId id() {
        return id;
    }

    public Collection<TodoItemTemplate> todoItemsTemplate() {
        return Collections.unmodifiableCollection(todoItemTemplates);
    }
}
