package gr.spring.drools.poc.conf.listeners;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class RuleExecutionListener implements AgendaEventListener {

    private int rulesFired = 0;

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        rulesFired++;
        log.info("Rule fired {}", event.getMatch().getRule().toString());
    }

    @Override
    public void matchCreated(MatchCreatedEvent event) {
        //log.info("Rule evaluated {}", event.getMatch().getRule().toString());
    }

    @Override
    public void matchCancelled(MatchCancelledEvent event) { }

    @Override
    public void beforeMatchFired(org.kie.api.event.rule.BeforeMatchFiredEvent event) { }

    @Override
    public void agendaGroupPopped(org.kie.api.event.rule.AgendaGroupPoppedEvent event) { }

    @Override
    public void agendaGroupPushed(org.kie.api.event.rule.AgendaGroupPushedEvent event) { }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) { }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) { }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) { }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) { }

    public int getRulesFired() {
        return rulesFired;
    }
}

