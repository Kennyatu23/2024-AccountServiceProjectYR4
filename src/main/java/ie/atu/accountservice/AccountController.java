package ie.atu.accountservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private AccountService accountservice;

    @PostMapping
    public AccountDetails createAccount(@RequestParam String accountType)
        return accountService.createAccount(accountType);

}
