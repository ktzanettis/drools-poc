package gr.spring.drools.poc.service;

import gr.spring.drools.poc.model.Participant;
import gr.spring.drools.poc.model.PayzyMember;
import gr.spring.drools.poc.model.Rate;
import gr.spring.drools.poc.model.db.Rule;
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

    public PayzyMember applyDbRulesInFact(PayzyMember member) {

        List<Rule> ruleList = ruleService.getRulesByStatus(Rule.Status.ACTIVE);
        String compiledRules = getCompiledRules(ruleList);
        log.info("Compiled rules: {}", compiledRules);
        KieSession session = createSession(compiledRules);

        //session.setGlobal("member", member);
        session.insert(member);
        int rulesMathced = session.fireAllRules();
        log.info("Number of matching rules: {}", rulesMathced);
        log.info("Member is eligible for offer... {}", member.isEligibleForOffer());
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
