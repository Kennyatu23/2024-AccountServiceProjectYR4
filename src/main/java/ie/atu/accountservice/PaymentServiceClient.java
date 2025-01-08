package ie.atu.accountservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Payment-Service", url = "http://localhost:8082")
public interface PaymentServiceClient {
    @PostMapping("balanceNotify")
    String getBalance(@RequestBody AccountDetails accountDetails);
}
