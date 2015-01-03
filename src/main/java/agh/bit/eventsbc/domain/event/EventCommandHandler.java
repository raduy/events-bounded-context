package agh.bit.eventsbc.domain.event;

import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EventCommandHandler {
    private Repository<Event> eventRepository;



    @Autowired
    @Qualifier("eventRepository")
    public void setEventRepository(Repository<Event> eventRepository) {
        this.eventRepository = eventRepository;
    }
}
