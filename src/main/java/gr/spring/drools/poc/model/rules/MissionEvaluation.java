package gr.spring.drools.poc.model.rules;

import lombok.Data;

import java.util.List;

@Data
public class MissionEvaluation {

    private int numOfPhysicalCard;
    private int numOfVirtualCard;
    private double numOfQrTrans;
    private int numOfValidatedRules = 0;
    private double amountCardTrans;
    private double amountQrTrans;
    private List<Transaction> transList;

    private boolean eligibleForOffer;

    /*X€ in the 1st card transaction, X€ in the 2nd card transaction, X€ in the 3rd card transaction,
    X card transactions at least X€ each
    X QR transaction of X€ in payzy pro spots*/

}
