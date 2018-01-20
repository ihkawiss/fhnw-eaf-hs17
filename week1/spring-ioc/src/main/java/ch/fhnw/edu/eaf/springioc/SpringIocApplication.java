package ch.fhnw.edu.eaf.springioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import ch.fhnw.edu.eaf.springioc.renderer.IMessageRenderer;

@SpringBootApplication
public class SpringIocApplication {

	public static void main(String[] args) {

		// Fundamental Spring Container using BeanFactory
		// BeanFactory factory = getBeanFactory();
		// IMessageRenderer renderer = (IMessageRenderer) factory.getBean("renderer");
		// renderer.render();

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/helloConfig.xml");
		IMessageRenderer renderer = context.getBean(IMessageRenderer.class);
		renderer.render();

	}

	public static BeanFactory getBeanFactory() {
		XmlBeanFactory xmlFactory = new XmlBeanFactory(new ClassPathResource("spring/helloConfig.xml"));
		return xmlFactory;
	}
}
