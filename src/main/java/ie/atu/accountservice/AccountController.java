package ie.atu.accountservice;


import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public AccountController(AccountService accountService, AccountDatabase accountDatabase, PaymentServiceClient paymentServiceClient, RabbitTemplate rabbitTemplate) {
        this.accountService = accountService;
        this.accountDatabase = accountDatabase;
        this.paymentServiceClient = paymentServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping
    public ResponseEntity<AccountDetails> createAccount(@Valid @RequestBody AccountDetails accountDetails) {
        AccountDetails newAccountDetails = accountService.createAccount(accountDetails);
        return ResponseEntity.ok(newAccountDetails);
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


    //Get balance from Payment Service
    @PostMapping("/balance-notification")
    public String notifyBalance(@RequestBody AccountDetails accountDetails){
        String balance = paymentServiceClient.getBalance(accountDetails);
        return balance;
    }

    @PostMapping("/sendToPaymentService")
    public String sendBalance(@RequestBody AccountDetails accountDetails) {
        String balanceMessage = String.format("Balance details for Account: %s, Account Type: %s, Balance: %.2f",
                accountDetails.getAccountName(), accountDetails.getAccountType(), accountDetails.getAccountBalance());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "balanceQueue", balanceMessage);
        return  balanceMessage;
    }


}
