package ie.atu.accountservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(AccountController.class)
class AccountControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountDatabase accountDatabase;

    @MockBean
    private PaymentServiceClient paymentServiceClient;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllCustomers() {

    }

    @Test
    void testGetAccountById() throws Exception {
        AccountDetails accountDetails = new AccountDetails("2","Jane", "Savings",250.00);
        when(accountService.getAccountByID("2")).thenReturn(Optional.of(accountDetails));

        mockMvc.perform(get("/accountDetails/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountName").value("Jane"));

    }

    @Test
    void testCreateAccount() throws Exception {
        AccountDetails accountDetails = new AccountDetails(null, "Joe", "Current", 1000.00);
        when(accountService.createAccount(any(AccountDetails.class))).thenReturn(accountDetails);

        ObjectMapper om = new ObjectMapper();
        String valueJson = om.writeValueAsString(accountDetails);

        mockMvc.perform(post("/accountDetails").contentType("application/json").content(valueJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountType").value("Current"));
    }

}

