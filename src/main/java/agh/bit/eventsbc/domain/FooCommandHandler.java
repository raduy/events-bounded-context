package agh.bit.eventsbc.domain;

import agh.bit.eventsbc.domain.commands.CreateFooCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class FooCommandHandler {
    private Repository<Foo> fooRepository;

    @CommandHandler
    public void handleCreateFooCommand(CreateFooCommand command) {

        Foo foo = new Foo(command.getId(), command.getDescription());
        this.fooRepository.add(foo);
    }

    public void setFooRepository(Repository<Foo> fooRepository) {
        this.fooRepository = fooRepository;
    }
}
