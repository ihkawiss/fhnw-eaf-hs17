package stomp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private SimpMessagingTemplate template;

	public void sendMessageToTopic(String name, String message) {
		String msg = String.format("[%s] %s", new Date(), message);
		this.template.convertAndSend("/topic/" + name, msg);
	}
}