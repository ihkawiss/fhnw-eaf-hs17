package stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StompRestController {
	@Autowired
	Sender sender;

	@RequestMapping("/send/{topicName}")
	public String sender(@PathVariable String topicName, @RequestParam String message) {
		sender.sendMessageToTopic(topicName, message);
		return "OK-Sent";
	}
}