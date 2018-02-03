package stomp;

import java.util.concurrent.CountDownLatch;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class Client3 {
	private static CountDownLatch cdl = new CountDownLatch(1);
	
	public static void main(String[] args) throws Exception {
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		webSocketClient.doHandshake(new Handler(), "ws://localhost:8080/stomp-nojs");
		cdl.await();
	}

	static CharSequence getConnectMessage() {
		return "CONNECT\r\n" + 
				"accept-version:1.0,1.1\r\n" + 
				"host:stomp.github.org\r\n" + 
				"^@";
	}
	
	static CharSequence getSubscribeMessage() {
		return "SUBSCRIBE\r\n" + 
				"id:0\r\n" + 
				"destination:/queue/a\r\n" + 
				"ack:client\r\n" + 
				"^@";
	}
	
	static class Handler extends TextWebSocketHandler {
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println("handleTextMessage " + message.getPayload());
			if(message.getPayload().startsWith("CONNECTED")) {
				session.sendMessage(new TextMessage(getSubscribeMessage()));
			}
		}
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("connected");
			session.sendMessage(new TextMessage(getConnectMessage()));
		}
	}

}
