package ie.atu.accountservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountDatabase extends MongoRepository<AccountDetails, String> {
}
