package ie.atu.accountservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface AccountDatabase extends MongoRepository<AccountDetails, String> {
}
