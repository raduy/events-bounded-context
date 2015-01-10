package agh.bit.eventsbc.domain.eventproposal.valueobjects;

import com.google.common.base.Preconditions;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 09.01.15.
 */

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class InterestThreshold {

    private final int interestThreshold;

    public boolean fulfilledWith(int interestCount) {
        Preconditions.checkArgument(interestCount >= 0);

        return interestCount >= interestThreshold;

    }
}
