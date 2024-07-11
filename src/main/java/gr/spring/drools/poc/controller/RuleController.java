package gr.spring.drools.poc.controller;

import gr.spring.drools.poc.model.db.Rule;
import gr.spring.drools.poc.service.RuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController ()
@RequestMapping ("/rules")
public class RuleController {

    private RuleService ruleService;

    public RuleController(final RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping ("/actions/getRuleByName/{ruleName}")
    public ResponseEntity<Rule> getRuleByName(@PathVariable String ruleName) {
        Rule rule = ruleService.getRuleByName(ruleName);
        return new ResponseEntity<>(rule, HttpStatus.OK);
    }

    @GetMapping ("")
    public ResponseEntity<List<Rule>> getAllRules() {
        List<Rule> ruleList = ruleService.getAllRules();
        return new ResponseEntity<>(ruleList, HttpStatus.OK);
    }

}
