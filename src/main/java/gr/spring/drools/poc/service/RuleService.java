package gr.spring.drools.poc.service;

import gr.spring.drools.poc.model.db.Rule;
import gr.spring.drools.poc.repo.RuleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    private RuleRepo ruleRepo;

    public RuleService(final RuleRepo ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    public Rule getRuleByName(String ruleName) {
        return ruleRepo.findByName(ruleName).orElse(null);
    }

    public List<Rule> getAllRules() {
        return ruleRepo.findAll();
    }
    public List<Rule> getRulesByStatus(Rule.Status status) {
        return ruleRepo.findByStatus(status);
    }
}
