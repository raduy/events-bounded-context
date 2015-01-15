package agh.bit.eventsbc.domain.todolist.builders;

import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;

import java.util.Arrays;
import java.util.List;

/**
 * Created by novy on 06.01.15.
 */
public class TemplateBuilder {

    private TodoListTemplateId id;
    private List<String> descriptions;


    public static TemplateBuilder builder() {
        return new TemplateBuilder();
    }

    public TemplateBuilder withId(TodoListTemplateId id) {
        this.id = id;
        return this;
    }

    public TemplateBuilder withDescriptions(String... descriptions) {
        this.descriptions = Arrays.asList(descriptions);
        return this;
    }

    public TodoListTemplate build() {
        return new TodoListTemplate(this.id, this.descriptions);
    }
}
