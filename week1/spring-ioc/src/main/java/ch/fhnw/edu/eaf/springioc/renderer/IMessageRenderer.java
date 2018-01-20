package ch.fhnw.edu.eaf.springioc.renderer;

import ch.fhnw.edu.eaf.springioc.provider.IMessageProvider;

public interface IMessageRenderer {

	public void render();

	public void setMessageProvider(IMessageProvider provider);

}
