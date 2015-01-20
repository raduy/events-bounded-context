package agh.bit.eventsbc.web.eventproposal.controller;

import agh.bit.eventsbc.config.WebMvcConfig;
import agh.bit.eventsbc.domain.eventproposal.builders.CreateEventProposalCommandBuilder;
import agh.bit.eventsbc.domain.eventproposal.commands.CreateEventProposalCommand;
import agh.bit.eventsbc.domain.eventproposal.valueobjects.EventDescription;
import agh.bit.eventsbc.query.eventproposal.model.EventProposalBasicView;
import agh.bit.eventsbc.query.eventproposal.repositories.EventProposalJpaRepository;
import agh.bit.eventsbc.web.eventproposal.forms.CreateEventProposalForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static agh.bit.eventsbc.web.eventproposal.controller.WithoutIdentifierMatcher.equalsOmittingId;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        WebMvcConfig.class, TestContext.class
})
public class EventProposalControllerTest {

    private static final String URL_PATH = "/eventproposal/";

    private final String eventProposalId = "anId";
    private final String eventProposalName = "aName";
    private final String eventDescription = "aDescription";
    private final int minimalInterestThreshold = 30;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CommandGateway commandGatewayMock;

    @Autowired
    private EventProposalJpaRepository eventProposalJpaRepositoryMock;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(commandGatewayMock, eventProposalJpaRepositoryMock);
    }

    @Test
    public void givenGetRequestShouldInvokeFindAllOnRepository() throws Exception {
        mockMvc.perform(
                get(URL_PATH)
        );

        verify(eventProposalJpaRepositoryMock, times(1)).findAll();
    }

    @Test
    public void givenGetRequestShouldReturnSerializedRepositoryContent() throws Exception {
        given(eventProposalJpaRepositoryMock.findAll())
                .willReturn(
                        ImmutableList.of(
                                new EventProposalBasicView(
                                        eventProposalName,
                                        minimalInterestThreshold,
                                        eventProposalId
                                )
                        )
                );


        mockMvc
                .perform(get(URL_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventProposalId", is(eventProposalId)))
                .andExpect(jsonPath("$[0].name", is(eventProposalName)))
                .andExpect(jsonPath("$[0].minimalInterestThreshold", is(minimalInterestThreshold)));

    }

    @Test
    public void givenPostRequestShouldReturnHttpCreated() throws Exception {
        final String jsonString = asJsonString(
                new CreateEventProposalForm(eventProposalName, eventDescription, minimalInterestThreshold)
        );

        mockMvc
                .perform(
                        post(URL_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andExpect(
                        status().isCreated()
                );
    }

    @Test
    public void givenPostRequestShouldSendAppropriateCommandViaCommandHandler() throws Exception {

        final String jsonString = asJsonString(
                new CreateEventProposalForm(eventProposalName, eventDescription, minimalInterestThreshold)
        );
        ArgumentCaptor<CreateEventProposalCommand> argumentCaptor = ArgumentCaptor.forClass(CreateEventProposalCommand.class);

        mockMvc.perform(
                post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
        );


        verify(commandGatewayMock, times(1)).send(argumentCaptor.capture());

        final CreateEventProposalCommand capturedCommand = argumentCaptor.getValue();
        final CreateEventProposalCommand expectedCommand = CreateEventProposalCommandBuilder
                .newCreateEventProposalCommand()
                .name(eventProposalName)
                .description(EventDescription.of(eventDescription))
                .minimalInterestThreshold(minimalInterestThreshold)
                .build();

        assertThat(capturedCommand, equalsOmittingId(expectedCommand));

    }

    private String asJsonString(CreateEventProposalForm form) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(form);
    }
}