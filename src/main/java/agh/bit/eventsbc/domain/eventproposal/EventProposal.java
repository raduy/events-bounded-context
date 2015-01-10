package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.common.IdentifiedDomainAggregateRoot;
import agh.bit.eventsbc.domain.eventproposal.entities.Student;
import agh.bit.eventsbc.domain.eventproposal.events.*;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventProposalId;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.MemberId;
import agh.bit.eventsbc.domain.todolist.valueobjects.TodoListId;
import com.google.common.collect.Lists;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.List;

/**
 * Created by novy handle 03.01.15.
 */
public class EventProposal extends IdentifiedDomainAggregateRoot<EventProposalId> {

    private String name;
    private TodoListId todoListId;
    private EventDescription description;
    private int minimalInterestThreshold;
    private List<Student> interestedStudents = Lists.newArrayList();

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

    public void registerInterestedStudent(MemberId memberId, String firstName, String lastName, String email) {
        if (studentAlreadyInterestedInEvent(memberId)) {
            apply(new MemberAlreadyInterestedInEventProposal(
                            id, memberId, firstName, lastName, email
                    )
            );

            return;
        }

        apply(new MemberSignedInterestEvent(
                        id, memberId, firstName, lastName, email
                )
        );


    }

    private boolean studentAlreadyInterestedInEvent(MemberId memberId) {
        return interestedStudents.stream()
                .anyMatch(student -> student.matchesId(memberId));
    }

    @EventSourcingHandler
    public void on(MemberSignedInterestEvent event) {
        interestedStudents.add(
                new Student(event.memberId(), event.firstName(), event.lastName(), event.email())
        );
    }

    private boolean alreadyHasTodoListAssigned() {
        return todoListId != null;
    }
}
