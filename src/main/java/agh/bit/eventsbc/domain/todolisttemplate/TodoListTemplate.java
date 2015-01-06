package agh.bit.eventsbc.domain.todolisttemplate;

import agh.bit.eventsbc.domain.todolisttemplate.events.TodoListTemplateCreatedEvent;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoItemTemplate;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Arrays;
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

    private Collection<TodoItemTemplate> templates = Lists.newArrayList();

    private TodoListTemplate() {}

    public TodoListTemplate(TodoListTemplateId id, List<String> todoItemDescriptions) {
        apply(new TodoListTemplateCreatedEvent(id, todoItemDescriptions));
    }

    @EventSourcingHandler
    public void on(TodoListTemplateCreatedEvent event) {
        this.id = event.id();
        this.templates = templatesFrom(event.todoItemDescriptions());
    }

    private Collection<TodoItemTemplate> templatesFrom(Collection<String> descriptions) {
        return descriptions.stream()
                .map(TodoItemTemplate::new)
                .collect(Collectors.toList());

    }

    public Collection<TodoItemTemplate> todoItemsTemplate() {
        return Collections.unmodifiableCollection(templates);
    }
}
