package gr.spring.drools.poc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayzyMember {

    private int physicalCardNumber;
    private int virtualCardNumber;
    private boolean eligibleForOffer = false;
}
