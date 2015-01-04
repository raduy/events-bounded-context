package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.commands.CreateEventCommand;
import agh.bit.eventsbc.domain.event.commands.FinishAttendeeGatheringCommand;
import agh.bit.eventsbc.domain.event.commands.SignForEventCommand;
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
    public void handleCreateEventCommand( CreateEventCommand command ) {
        Event event = EventFactory.create(
                command.eventId,
                command.name,
                command.maxAttendeesCount );
        eventRepository.add( event );
    }

    @CommandHandler
    public void handleSignForEventCommand( SignForEventCommand command ) {
        Event event = eventRepository.load( command.eventId );
        event.addAttendeeToEvent(
                command.attendeeId,
                command.email,
                command.firstname,
                command.lastname );
    }

    @CommandHandler
    public void handleFinishAttendeeGatheringCommand( FinishAttendeeGatheringCommand command ) {
        Event event = eventRepository.load( command.eventId );
        event.finishAttendeeGathering( );
    }

    @Autowired
    @Qualifier( "eventRepository" )
    public void setEventRepository( Repository<Event> eventRepository ) {
        this.eventRepository = eventRepository;
    }
}
