package ch.fhnw.edu.eaf.springioc.renderer;

import ch.fhnw.edu.eaf.springioc.provider.IMessageProvider;

public class StandardErrorXMLRenderer implements IMessageRenderer {

	IMessageProvider provider = null;

	@Override
	public void render() {
		System.err.println("Simulate XML output: " + provider.getMessage());
	}

	@Override
	public void setMessageProvider(IMessageProvider provider) {
		this.provider = provider;
	}

}
