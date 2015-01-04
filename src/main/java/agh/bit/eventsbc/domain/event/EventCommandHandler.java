package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.commands.CreateEventCommand;
import agh.bit.eventsbc.domain.event.factories.EventFactory;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EventCommandHandler {
    private Repository<Event> eventRepository;

    @CommandHandler
    public void handleCreateEventCommand( CreateEventCommand createEventCommand ) {
        Event event = EventFactory.create( createEventCommand.eventId, createEventCommand.name, createEventCommand.maxAttendeesCount );
        eventRepository.add( event );
    }

    @Autowired
    @Qualifier( "eventRepository" )
    public void setEventRepository( Repository<Event> eventRepository ) {
        this.eventRepository = eventRepository;
    }
}
