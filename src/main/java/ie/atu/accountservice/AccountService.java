package ie.atu.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private AccountDatabase accountDatabase;

    public AccountDetails createAccount(String accountType) {
        //using lombok builder pattern
        AccountDetails account = AccountDetails.builder()
                .accountType(accountType)
                .build();
        return acccountDatabase.save(account);
    }
}
