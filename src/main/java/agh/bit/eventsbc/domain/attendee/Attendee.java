package agh.bit.eventsbc.domain.attendee;

import agh.bit.eventsbc.domain.common.IdentifiedDomainEntity;
import com.google.common.base.Preconditions;
import lombok.experimental.Accessors;
import org.apache.commons.validator.routines.EmailValidator;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

//todo: make it value object completely?
@Accessors(fluent = true)
public class Attendee extends IdentifiedDomainEntity<AttendeeId> {

    private String email;
    private String firstName;
    private String lastName;

    public Attendee(AttendeeId attendeeId, String email, String firstName, String lastName) {
        super(attendeeId);
        checkNotNull(firstName);
        checkNotNull(lastName);

        boolean isEmailValid = EmailValidator.getInstance().isValid(email);
        Preconditions.checkArgument(isEmailValid, format("Incorrect email for user: %s %s", firstName, lastName));

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
