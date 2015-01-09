package agh.bit.eventsbc.domain.eventproposalrequest;

import agh.bit.eventsbc.domain.common.FixtureFactory;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;

public class EventProposalRequestPreconfiguredTestCase {
    protected FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = FixtureFactory.onEventProposalRequestPreconfiguredFixture();
    }

}
