package me.spike.springbootproviderpact;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.SelectorBuilder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@WebMvcTest
@Provider("springboot-provider-pact")
@PactBroker
@IgnoreNoPactsToVerify
public class ContractVerificationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    // Refer https://docs.pact.io/implementation_guides/jvm/provider/junit5
    @au.com.dius.pact.provider.junitsupport.loader.PactBrokerConsumerVersionSelectors
    public static SelectorBuilder consumerVersionSelectors() {
        // Select Pacts for consumers deployed to production with branch from CI build
        return new SelectorBuilder()
                .deployedTo("prod")
                .branch("main");
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        if (context != null) {
            context.verifyInteraction();
        }
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        if (context != null) {
            context.setTarget(new MockMvcTestTarget(mockMvc));
        }
    }

    @State(value = "greetingAKnownPerson", action = StateChangeAction.SETUP)
    public void whenGreetingAKnownPerson() {
        when(greetingService.greet("John")).thenReturn(new Greeting("John", "Doe", "Hello John Doe!"));
    }

    @State(value = "greetingAnUnknownPerson", action = StateChangeAction.SETUP)
    public void whenGreetingAnUnknownPerson() {
        when(greetingService.greet("Unknown")).thenReturn(null);
    }
}
