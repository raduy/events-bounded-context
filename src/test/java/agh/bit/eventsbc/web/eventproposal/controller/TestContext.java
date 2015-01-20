package agh.bit.eventsbc.web.eventproposal.controller;

import agh.bit.eventsbc.query.eventproposal.repositories.EventProposalJpaRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * Created by novy on 20.01.15.
 */
@Configuration
class TestContext {

    @Bean
    public EventProposalController eventProposalController() {
        return new EventProposalController();
    }

    @Bean
    public CommandGateway commandGateway() {
        return mock(CommandGateway.class);
    }

    @Bean
    public EventProposalJpaRepository eventProposalJpaRepository() {
        return mock(EventProposalJpaRepository.class);
    }

}
