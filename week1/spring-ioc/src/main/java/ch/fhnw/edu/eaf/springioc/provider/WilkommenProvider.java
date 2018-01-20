package ch.fhnw.edu.eaf.springioc.provider;

public class WilkommenProvider implements IMessageProvider {

	@Override
	public String getMessage() {
		return "Wilkommen!";
	}

}
