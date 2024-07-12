package gr.spring.drools.poc.model.rules;

import lombok.Data;

@Data
public class Transaction {

    private int order;
    private double amount;
    private TransType type;

    public enum TransType {
        CARD, QR
    }

}
