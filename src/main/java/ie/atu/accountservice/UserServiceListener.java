package ie.atu.accountservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserServiceListener {
    @RabbitListener(queues = "accountQueue")
    public void accountInformation(String message) {
        System.out.println(message);
    }
}
