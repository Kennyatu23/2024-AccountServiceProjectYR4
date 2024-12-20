package ie.atu.accountservice;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/accountDetails")
public class AccountController {

    private  AccountDatabase accountDatabase;
    private List<AccountDetails> accountDetailsList = new ArrayList<>();
    private AccountService accountService;

    public AccountController(AccountService accountService, AccountDatabase accountDatabase) {
        this.accountService = accountService;
        this.accountDatabase = accountDatabase;
    }


    @PostMapping
    public ResponseEntity<List<AccountDetails>> createAccount(@Valid @RequestBody AccountDetails accountDetails) {
        accountDetailsList = accountService.createAccount(accountDetails);
        return ResponseEntity.ok(accountDetailsList);
    }

    @PostMapping("/ConfirmMessage")
    public String accountConfirm(@RequestBody AccountDetails accountDetails) {
        String confirmMessage = String.format("Recieved details for Account ID: %d  Account Holder Name: %s  AccountType: %s",
                accountDetails.getAccountID(), accountDetails.getAccountName(), accountDetails.getAccountType());
        return confirmMessage;
    }

    @PutMapping("/{id}")
      public  ResponseEntity<List<AccountDetails>>upDateAccount(@PathVariable String id, @RequestBody AccountDetails NewAccountDetails) {
        accountDetailsList = accountService.upDateAccount(id, NewAccountDetails);
      return ResponseEntity.ok(accountDetailsList);
    }

    @GetMapping("/{id}")
    public Optional<AccountDetails>getAccountByID(@PathVariable String id) {
        return  accountService.getAccountByID(id);

    }

    @GetMapping
    public List<AccountDetails> getAllCustomers() {
        return accountDatabase.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<AccountDetails>> deleteAccount(@PathVariable String id) {
        accountDetailsList = accountService.deleteAccount(id);
        return ResponseEntity.ok(accountDetailsList);
    }




}
