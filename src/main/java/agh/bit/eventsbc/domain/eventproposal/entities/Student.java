package agh.bit.eventsbc.domain.eventproposal.entities;

import agh.bit.eventsbc.domain.common.IdentifiedDomainEntity;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.MemberId;

/**
 * Created by novy on 08.01.15.
 */
// todo: Should this be an entity? Also, this looks exactly like Attendee right now
public class Student extends IdentifiedDomainEntity<MemberId> {

    private String firstName;
    private String lastName;
    private String email;

    public Student(MemberId memberId, String firstName, String lastName, String email) {
        super(memberId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
