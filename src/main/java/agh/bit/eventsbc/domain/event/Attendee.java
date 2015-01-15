package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;
import com.google.common.base.Preconditions;
import groovy.transform.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Accessors;
import org.apache.commons.validator.routines.EmailValidator;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

import static java.lang.String.format;

@Value
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = false)
public class Attendee extends AbstractAnnotatedEntity {

    private AttendeeId attendeeId;
    private String email;
    private String firstName;
    private String lastName;

    public Attendee(AttendeeId attendeeId, String email, String firstName, String lastName) {
        Preconditions.checkNotNull(firstName);
        Preconditions.checkNotNull(lastName);

        boolean isEmailValid = EmailValidator.getInstance().isValid(email);
        Preconditions.checkArgument(isEmailValid, format("Incorrect email for user: %s %s", firstName, lastName));

        this.attendeeId = attendeeId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
