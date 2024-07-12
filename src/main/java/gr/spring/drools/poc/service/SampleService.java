package gr.spring.drools.poc.service;

import gr.spring.drools.poc.conf.listeners.RuleExecutionListener;
import gr.spring.drools.poc.model.Participant;
import gr.spring.drools.poc.model.PayzyMember;
import gr.spring.drools.poc.model.Rate;
import gr.spring.drools.poc.model.db.Rule;
import gr.spring.drools.poc.model.rules.MissionEvaluation;
import lombok.extern.slf4j.Slf4j;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class SampleService {

    private KieSession kieSession;
    private RuleService ruleService;

    private static int REQUIRED_VALID_ACTIVATION_GROUP_RULES = 2;

    public SampleService(KieSession kieSession, RuleService ruleService) {
        this.kieSession = kieSession;
        this.ruleService = ruleService;
    }

    public Rate getRate(Participant applicantRequest) {
        Rate rate = new Rate();
        kieSession.setGlobal("rate", rate);
        kieSession.insert(applicantRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return rate;
    }

    public PayzyMember getMissionEligibility(PayzyMember member) {

        kieSession.setGlobal("member", member);
        kieSession.insert(member);
        kieSession.fireAllRules();
        //kieSession.dispose();
        return member;
    }

    public MissionEvaluation applyDbRulesInFact(MissionEvaluation member) {

        List<Rule> ruleList = ruleService.getRulesByStatus(Rule.Status.ACTIVE);
        String compiledRules = getCompiledRules(ruleList);
        log.info("Compiled rules: {}", compiledRules);
        KieSession session = createSession(compiledRules);

        // Add an event listener that logs the fired rules
        RuleExecutionListener listener = new RuleExecutionListener();
        session.addEventListener(listener);

        //session.setGlobal("member", member);
        session.insert(member);
        int rulesMatched = session.fireAllRules();
        log.info("Number of matching rules: {}", rulesMatched);
        log.info("Number of rules validated... {}", member.getNumOfValidatedRules());
        boolean userValid = false;
        if (rulesMatched >= REQUIRED_VALID_ACTIVATION_GROUP_RULES) {
            userValid = true;
        }
        log.info("User validity is {}", userValid);
        session.dispose();
        return member;
    }

    /**
     * Compile rule based on template
     */
    private String getCompiledRules(List<Rule> rules) {
        try {
            //InputStream templateStream = this.getClass().getResourceAsStream("rules/rule_template.drl");

            File file = ResourceUtils.getFile("classpath:rules/rule_template.drl");
            InputStream in = new FileInputStream(file);

            return new ObjectDataCompiler().compile(rules, in);
        } catch (FileNotFoundException fnfe) {
            log.error("File not found", fnfe);
        }
        return null;
    }

    private static KieSession createSession(String compiledRules) {
        log.info("KIE DB session session created...");
        return new KieHelper().addContent(compiledRules, ResourceType.DRL).build().newKieSession();
    }

}
