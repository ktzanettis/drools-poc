package gr.spring.drools.poc.service;

import gr.spring.drools.poc.model.Participant;
import gr.spring.drools.poc.model.PayzyMember;
import gr.spring.drools.poc.model.Rate;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

    private KieSession kieSession;

    public DroolsService(final KieSession kieSession) {
        this.kieSession = kieSession;
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

}
