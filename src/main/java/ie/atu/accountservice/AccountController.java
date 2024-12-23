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

    @PostMapping("/accountsRegister")
    public String accountConfirm(@RequestBody Person person) {
        String confirmMessage = String.format("Received details for Account Holder Name: %s  Email: %s  AccountType: %s",
                 person.getName(), person.getEmail(), person.getAccountType());
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
