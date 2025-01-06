package ie.atu.accountservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountDatabase accountDatabase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAccountDetails()
    {
        AccountDetails accountDetails = new AccountDetails("3", "Kenny", "Current", 300.00);
        when(accountDatabase.save(accountDetails)).thenReturn(accountDetails);
        AccountDetails expectedAccountDetails = accountService.createAccount(accountDetails);
        assertEquals(300.00, expectedAccountDetails.getAccountBalance());
    }
}
