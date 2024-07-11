package gr.spring.drools.poc.repo;

import gr.spring.drools.poc.model.db.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleRepo extends MongoRepository<Rule, String> {
    Optional<Rule> findByName(String ruleName);

    List<Rule> findByStatus(Rule.Status status);
}
