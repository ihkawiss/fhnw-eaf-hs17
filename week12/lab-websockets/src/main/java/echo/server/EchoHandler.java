package echo.server;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoHandler extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		System.out.println("handleTextMessage: " + message.getPayload());
		String msg = String.format("Echo: %s [%s]", message.getPayload(), new Date());
		session.sendMessage(new TextMessage(msg));
	}

}
