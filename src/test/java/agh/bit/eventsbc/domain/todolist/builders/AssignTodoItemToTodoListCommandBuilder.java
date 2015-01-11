package agh.bit.eventsbc.domain.todolist.builders;

import agh.bit.eventsbc.domain.todolist.commands.AssignTodoItemToTodoListCommand;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoItemId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Created by novy on 10.01.15.
 */

@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor(staticName = "newAssignTodoItemToTodoListCommand")
public class AssignTodoItemToTodoListCommandBuilder {

    private TodoListId todoListId = TodoListId.of("123");
    private TodoItemId todoItemId = TodoItemId.of("123");
    private String description = "default description";
    private LocalDate createdAt = LocalDate.of(2010, 1, 1);


    public AssignTodoItemToTodoListCommand build() {
        return new AssignTodoItemToTodoListCommand(
                todoListId,
                todoItemId,
                description,
                createdAt
        );
    }
}
