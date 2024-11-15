package ie.atu.accountservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// This interface handles CRUD operations for AccountDetails
@Repository
public interface AccountDatabase extends MongoRepository<AccountDetails, String> {
}
