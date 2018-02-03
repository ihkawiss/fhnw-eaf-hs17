package stomp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

public class Client1 {
	private static CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
//		WebSocketClient webSocketClient = new StandardWebSocketClient();
		List<Transport> transports = new ArrayList<>(2);
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		transports.add(new RestTemplateXhrTransport());

		WebSocketClient webSocketClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		stompClient.setMessageConverter(new StringMessageConverter());
		// stompClient.setTaskScheduler(taskScheduler); // for heartbeats
		
		String url = "ws://localhost:8080/stomp";
		StompSessionHandler sessionHandler = new MyStompSessionHandler();
		stompClient.connect(url, sessionHandler);
		cdl.await();
	}

	private static class MyStompSessionHandler extends StompSessionHandlerAdapter {

	    @Override
	    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
	        System.out.println("connected");
	        session.send("/topic/message", "message1");
	        session.send("/app/hello", "message2");
	        cdl.countDown();
	    }
	}
}
