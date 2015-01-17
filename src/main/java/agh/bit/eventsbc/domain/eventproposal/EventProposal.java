package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.attendee.Attendee;
import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.common.IdentifiedDomainAggregateRoot;
import agh.bit.eventsbc.domain.eventproposal.events.*;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposal extends IdentifiedDomainAggregateRoot<EventProposalId> {

    private String name;
    private TodoListId todoListId;
    private EventDescription description;
    private int minimalInterestThreshold;
    private List<Attendee> interestedStudents = new ArrayList<>();

    private EventProposal() {
    }

    public EventProposal(EventProposalId eventProposalId, String name,
                         EventDescription description, int requiredInterestThreshold) {
        apply(new EventProposalCreatedEvent(eventProposalId, name, description, requiredInterestThreshold));
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
        this.description = event.description();
        this.minimalInterestThreshold = event.minimalInterestThreshold();
    }

    @EventSourcingHandler
    public void on(TodoListAssignedToEventProposalEvent event) {
        todoListId = event.todoListId();
    }

    public void registerInterestedStudent(AttendeeId memberId, String firstName, String lastName, String email) {
        if (attendeeAlreadyInterestedInEvent(memberId)) {
            apply(new AttendeeAlreadyInterestedInEventProposalEvent(id, memberId, firstName, lastName, email));
            return;
        }

        apply(new AttendeeSignedInterestEvent(id, memberId, firstName, lastName, email));
    }

    private boolean attendeeAlreadyInterestedInEvent(AttendeeId memberId) {
        return interestedStudents.stream()
                .anyMatch(student -> student.matchesId(memberId));
    }

    @EventSourcingHandler
    public void on(AttendeeSignedInterestEvent evt) {
        Attendee attendee = new Attendee(evt.memberId(), evt.email(), evt.lastName(), evt.firstName());
        interestedStudents.add(attendee);
    }

    private boolean alreadyHasTodoListAssigned() {
        return todoListId != null;
    }
}
