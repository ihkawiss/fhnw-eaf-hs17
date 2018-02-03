package echo.client;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@SpringBootApplication
public class EchoClient {
	
	private CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) {
		new SpringApplicationBuilder(EchoClient.class).web(false).run(args);
	}

	@Bean
	CommandLineRunner sendRequest() {
		return args -> {
			StandardWebSocketClient client = new StandardWebSocketClient();
			ListenableFuture<WebSocketSession> future = client.doHandshake(new TextWebSocketHandler() {
				@Override
				protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
					System.out.println("received message: " + message.getPayload());
					cdl.countDown();
				}

			}, new WebSocketHttpHeaders(), new URI("ws://localhost:8080/echo"));
			WebSocketSession session = future.get();

			String msg = "Hello there...";
			System.out.println("sending message: " + msg);
			WebSocketMessage<String> message = new TextMessage(msg);
			session.sendMessage(message);
			
			cdl.await();
		};
	}

}
