package ie.atu.accountservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountDetails {
    @Id
    private String accountID;

    // Field to specify the type of account (e.g, Loan, Savings
    private String accountType;



}
