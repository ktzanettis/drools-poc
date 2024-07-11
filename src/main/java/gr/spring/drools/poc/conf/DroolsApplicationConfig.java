package gr.spring.drools.poc.conf;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@Slf4j
public class DroolsApplicationConfig {

    private static final KieServices kieServices = KieServices.Factory.get();
    private static final String RULES_CUSTOMER_RULES_DRL = "rules/loan_rate.drl";
    private static final String RULES_PAYZY_MEMBER_RULES_DRL = "rules/payzy_mission.drl";

    @Bean
    public KieContainer getKieContainer() {
        // Get Drool configuration file
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PAYZY_MEMBER_RULES_DRL));

        // Create and return a new KIE container
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }

    @Bean
    public KieSession getKieSession() throws IOException {
        log.info("KIE session created...");
        return getKieContainer().newKieSession();
    }

}
