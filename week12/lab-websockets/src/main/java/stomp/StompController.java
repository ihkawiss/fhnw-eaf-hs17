package stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

    @MessageMapping("/hello")
    @SendTo("/topic/message")
    public String greeting(String message) throws Exception {
    	System.out.println(Thread.currentThread());
        Thread.sleep(1000); // simulated delay
        return "Hello, " + message + "!";
    }

}