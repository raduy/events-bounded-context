package agh.bit.eventsbc.domain.eventproposal.valueobjects;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InterestThresholdTest {

    private final InterestThreshold objectUnderTest =
            InterestThreshold.of(16);

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionGivenNegativeNumber() throws Exception {

        objectUnderTest.fulfilledWith(-5);

    }

    @Test
    public void shouldReturnFalseGivenLessInterestCount() throws Exception {

        Assertions.assertThat(objectUnderTest.fulfilledWith(10)).isFalse();

    }

    @Test
    public void shouldReturnTrueOtherwise() throws Exception {

        Assertions.assertThat(objectUnderTest.fulfilledWith(16)).isTrue();
        Assertions.assertThat(objectUnderTest.fulfilledWith(666)).isTrue();
    }
}