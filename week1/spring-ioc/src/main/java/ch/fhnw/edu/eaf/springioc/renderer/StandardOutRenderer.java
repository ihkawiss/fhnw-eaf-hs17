package ch.fhnw.edu.eaf.springioc.renderer;

import ch.fhnw.edu.eaf.springioc.provider.IMessageProvider;

public class StandardOutRenderer implements IMessageRenderer {

	private IMessageProvider provider = null;
	
	@Override
	public void render() {
		System.out.println(provider.getMessage());
	}

	@Override
	public void setMessageProvider(IMessageProvider provider) {
		this.provider = provider;
	}

}
