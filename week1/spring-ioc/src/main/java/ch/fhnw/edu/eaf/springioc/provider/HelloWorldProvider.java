package ch.fhnw.edu.eaf.springioc.provider;

public class HelloWorldProvider implements IMessageProvider {

	@Override
	public String getMessage() {
		return "Hello World!";
	}

}
