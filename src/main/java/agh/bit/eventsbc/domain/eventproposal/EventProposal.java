package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.eventproposal.events.EventProposalAlreadyHasTodoListEvent;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.events.TodoListAssignedToEventProposalEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.TodoList;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposal extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private EventProposalId eventProposalId;
    private String name;
    private TodoListId todoListId;
    private EventDescription description;

    private EventProposal() {}

    public EventProposal(EventProposalId eventProposalId, String name, EventDescription description) {
        apply(new EventProposalCreatedEvent(eventProposalId, name, description));
    }

    private void setEventProposalId(EventProposalId eventProposalId) {
        this.eventProposalId = eventProposalId;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void assignTodoList(TodoListId todoListId) {
        if (alreadyHasTodoListAssigned()) {
            apply(new EventProposalAlreadyHasTodoListEvent(eventProposalId));
            return;
        }

        apply(new TodoListAssignedToEventProposalEvent(eventProposalId, todoListId));
    }


    @EventSourcingHandler
    public void on(EventProposalCreatedEvent event) {
        this.setEventProposalId(event.eventProposalId());
        this.setName(event.name());
    }

    @EventSourcingHandler
    public void on(TodoListAssignedToEventProposalEvent event) {
        todoListId = event.todoListId();
    }

    private boolean alreadyHasTodoListAssigned() {
        return todoListId != null;
    }
}
