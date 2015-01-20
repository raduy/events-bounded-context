package agh.bit.eventsbc.web.eventproposal.controller;

import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

/**
 * Created by novy on 20.01.15.
 */
class WithoutIdentifierMatcher {

    public static Matcher<CreateEventProposalCommand> equalsOmittingId(CreateEventProposalCommand thatCommand) {
        return new TypeSafeMatcher<CreateEventProposalCommand>() {
            @Override
            public boolean matchesSafely(CreateEventProposalCommand thisCommand) {
                return new EqualsBuilder()
                        .append(thisCommand.name(), thatCommand.name())
                        .append(thisCommand.description(), thatCommand.description())
                        .append(thisCommand.minimalInterestThreshold(), thatCommand.minimalInterestThreshold())
                        .isEquals();
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}
