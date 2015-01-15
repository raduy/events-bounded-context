package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.common.FixtureFactory;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;

public class EventPreconfiguredTestCase {
    protected FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = FixtureFactory.onEventPreconfiguredFixture();
    }
}
