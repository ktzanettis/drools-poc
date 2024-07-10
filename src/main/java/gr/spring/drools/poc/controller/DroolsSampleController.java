package gr.spring.drools.poc.controller;

import gr.spring.drools.poc.model.Participant;
import gr.spring.drools.poc.model.PayzyMember;
import gr.spring.drools.poc.model.Rate;
import gr.spring.drools.poc.service.DroolsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController ()
@RequestMapping ("/bankService")
public class DroolsSampleController {

    private DroolsService bankService;

    public DroolsSampleController(final DroolsService bankService) {
        this.bankService = bankService;
    }

    @PostMapping ("/getRate")
    public ResponseEntity<Rate> getRate(@RequestBody Participant request) {
        Rate rate = bankService.getRate(request);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @PostMapping ("/getMissionEligibility")
    public ResponseEntity<PayzyMember> getMissionEligibility(@RequestBody PayzyMember member) {
        PayzyMember memberOutcome = bankService.getMissionEligibility(member);
        return new ResponseEntity<>(memberOutcome, HttpStatus.OK);
    }

}
