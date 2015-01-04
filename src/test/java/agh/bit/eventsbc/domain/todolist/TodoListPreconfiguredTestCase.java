package agh.bit.eventsbc.domain.todolist;

import agh.bit.eventsbc.domain.common.FixtureFactory;
import org.axonframework.test.FixtureConfiguration;
import org.junit.Before;

abstract class TodoListPreconfiguredTestCase {

    protected FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = FixtureFactory.onTodoListPreconfiguredFixture();
    }
}