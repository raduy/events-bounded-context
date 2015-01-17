package agh.bit.eventsbc.domain.todolist.builders;

import agh.bit.eventsbc.domain.todolist.commandhandlers.FulfillTodoListWithTemplateCommand;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import agh.bit.eventsbc.domain.todolisttemplate.valueobjects.TodoListTemplateId;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Created by novy on 10.01.15.
 */

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "newFulfillTodoListWithTemplateCommand")
public class FulfillTodoListWithTemplateCommandBuilder {

    private TodoListId todoListId = new TodoListId();
    private TodoListTemplateId todoListTemplateId = TodoListTemplateId.of("123");
    private LocalDate creationDate = LocalDate.of(2010, 1, 1);

    public FulfillTodoListWithTemplateCommand build() {
        return new FulfillTodoListWithTemplateCommand(
                todoListId,
                todoListTemplateId,
                creationDate
        );
    }
}
