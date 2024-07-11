package gr.spring.drools.poc.controller;

import gr.spring.drools.poc.model.Participant;
import gr.spring.drools.poc.model.PayzyMember;
import gr.spring.drools.poc.model.Rate;
import gr.spring.drools.poc.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController ()
@RequestMapping ("/sample")
public class SampleController {

    private SampleService droolsService;

    public SampleController(final SampleService droolsService) {
        this.droolsService = droolsService;
    }

    @PostMapping ("/getRate")
    public ResponseEntity<Rate> getRate(@RequestBody Participant request) {
        Rate rate = droolsService.getRate(request);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @PostMapping ("/getMissionEligibility")
    public ResponseEntity<PayzyMember> getMissionEligibility(@RequestBody PayzyMember member) {
        PayzyMember memberOutcome = droolsService.getMissionEligibility(member);
        return new ResponseEntity<>(memberOutcome, HttpStatus.OK);
    }

    @PostMapping ("/applyDbRulesInFact")
    public ResponseEntity<PayzyMember> applyDbRulesInFact(@RequestBody PayzyMember member) {
        PayzyMember memberOutcome = droolsService.applyDbRulesInFact(member);
        return new ResponseEntity<>(memberOutcome, HttpStatus.OK);
    }

}
