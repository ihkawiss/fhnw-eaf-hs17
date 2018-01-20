package ch.fhnw.edu.eaf.springioc.provider;

public class ExternalizedConstructorMessageProvider implements IMessageProvider {

	private String message;
	
	public ExternalizedConstructorMessageProvider(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
