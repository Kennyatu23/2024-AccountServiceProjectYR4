package ie.atu.accountservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor

// account fields
public class AccountDetails {

    @Id
    private String accountID;
    @NotBlank(message = "Name must not be blank please")
    private String accountName;

    // Field to specify the type of account (e.g, Loan, Savings)
    @NotBlank(message = "Account Type must not be blank")
    private String accountType;
    @NotNull(message = "Balance cannot be null")
    private Double accountBalance;


}
