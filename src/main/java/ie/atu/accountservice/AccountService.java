package ie.atu.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    private AccountDatabase accountDatabase;
    private List<AccountDetails> accountDetailsList = new ArrayList<>();

    // Creating constructor,injection of database into this class

    @Autowired
    public AccountService(AccountDatabase accountDatabase) {

        this.accountDatabase = accountDatabase;
    }
    public List<AccountDetails> createAccount(AccountDetails accountDetails) {
        accountDatabase.save(accountDetails);
        return accountDatabase.findAll();

    }
    public Optional<AccountDetails> getAccountByID(String id) {
        return accountDatabase.findById(id);

    }
    public List<AccountDetails> upDateAccount(String accountID, AccountDetails accountDetails) {

        if(accountDatabase.existsById(accountID)) {
            accountDetails.setAccountID(accountID);
            accountDatabase.save(accountDetails);
        }
        return  accountDatabase.findAll();
    }
    public List<AccountDetails> deleteAccount(String id) {
        if(accountDatabase.existsById(id)) {
            accountDatabase.deleteById(id);
        }
        return accountDatabase.findAll();
    }
}
