package agh.bit.eventsbc.config;

import agh.bit.eventsbc.domain.event.Event;
import agh.bit.eventsbc.domain.eventproposal.EventProposal;
import agh.bit.eventsbc.domain.eventproposalrequest.EventProposalRequest;
import agh.bit.eventsbc.domain.todolist.TodoList;
import agh.bit.eventsbc.domain.todolisttemplate.TodoListTemplate;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.java.SimpleEventSchedulerFactoryBean;
import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AxonConfig {

    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
        processor.setEventBus(eventBus());
        return processor;
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
        processor.setCommandBus(commandBus());
        return processor;
    }

    @Bean
    public CommandBus commandBus() {
        return new SimpleCommandBus();
    }

    @Bean
    public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
        CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
        factory.setCommandBus(commandBus());
        return factory;
    }

    @Bean
    public EventScheduler eventScheduler() throws Exception {
        return new SimpleEventSchedulerFactoryBean().getObject();
    }

    // TODO: refactor
    @Bean
    public EventStore eventStore() {
        return new FileSystemEventStore(new SimpleEventFileResolver(new File("data/eventstore")));
    }

    @Bean
    public Repository<Event> eventRepository() {
        EventSourcingRepository<Event> repository = new EventSourcingRepository<Event>(
                Event.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public Repository<EventProposal> eventProposalRepository() {
        EventSourcingRepository<EventProposal> repository = new EventSourcingRepository<EventProposal>(
                EventProposal.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public Repository<EventProposalRequest> eventProposalRequestRepository() {
        EventSourcingRepository<EventProposalRequest> repository = new EventSourcingRepository<EventProposalRequest>(
                EventProposalRequest.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public Repository<TodoList> todoListRepository() {
        EventSourcingRepository<TodoList> repository = new EventSourcingRepository<TodoList>(
                TodoList.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public Repository<TodoListTemplate> todoListTemplateRepository() {
        EventSourcingRepository<TodoListTemplate> repository = new EventSourcingRepository<TodoListTemplate>(
                TodoListTemplate.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

}
