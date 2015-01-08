package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.common.IdentifiedDomainAggregateRoot;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalAlreadyHasTodoListEvent;
import agh.bit.eventsbc.domain.eventproposal.events.EventProposalCreatedEvent;
import agh.bit.eventsbc.domain.eventproposal.events.TodoListAssignedToEventProposalEvent;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposal extends IdentifiedDomainAggregateRoot<EventProposalId> {

    private String name;
    private TodoListId todoListId;
    private EventDescription description;

    private EventProposal() {
    }

    public EventProposal(EventProposalId eventProposalId, String name, EventDescription description) {
        apply(new EventProposalCreatedEvent(eventProposalId, name, description));
    }

    public void assignTodoList(TodoListId todoListId) {
        if (alreadyHasTodoListAssigned()) {
            apply(new EventProposalAlreadyHasTodoListEvent(id));
            return;
        }

        apply(new TodoListAssignedToEventProposalEvent(id, todoListId));
    }


    @EventSourcingHandler
    public void on(EventProposalCreatedEvent event) {
        this.id = event.eventProposalId();
        this.name = event.name();
    }

    @EventSourcingHandler
    public void on(TodoListAssignedToEventProposalEvent event) {
        todoListId = event.todoListId();
    }

    private boolean alreadyHasTodoListAssigned() {
        return todoListId != null;
    }
}
