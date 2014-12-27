package agh.bit.eventsbc.domain;

import agh.bit.eventsbc.domain.commands.CreateFooCommand;
import agh.bit.eventsbc.domain.events.FooCreatedEvent;
import agh.bit.eventsbc.domain.valueobject.FooId;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class FooTest {
    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(Foo.class);

        FooCommandHandler commandHandler = new FooCommandHandler();
        commandHandler.setFooRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void createEnrollmentCommandEventShouldCreateNewEnrollment() throws Exception {
        FooId id = new FooId("394359-34");
        fixture.given()
                .when(
                        new CreateFooCommand(id, "This is description")
                )
                .expectEvents(
                        new FooCreatedEvent(id, "This is description")
                );
    }

}
