package gr.spring.drools.poc.model.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "rules")
@Data
public class Rule {
    @Id
    private String id;
    private String name;
    private int priority;
    private String activationGroup;
    private String agendaGroup;
    private String dateStart;
    private String dateEnd;
    private Status status;
    private String expression;
    private boolean eligible;

    public enum Status {
        ACTIVE, NON_ACTIVE
    }

}
