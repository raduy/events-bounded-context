package agh.bit.eventsbc.domain.todolisttemplate;

import agh.bit.eventsbc.domain.common.FixtureFactory;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;

abstract class TodoListTemplatePreconfiguredTestCase {

    protected FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = FixtureFactory.onTodoListTemplatePreconfiguredFixture();
    }
}