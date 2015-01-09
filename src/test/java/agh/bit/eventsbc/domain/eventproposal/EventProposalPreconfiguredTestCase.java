package agh.bit.eventsbc.domain.eventproposal;

import agh.bit.eventsbc.domain.common.FixtureFactory;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;

/**
 * Created by novy on 04.01.15.
 */
abstract class EventProposalPreconfiguredTestCase {

    protected FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = FixtureFactory.onEventProposalPreconfiguredFixture();
    }
}
