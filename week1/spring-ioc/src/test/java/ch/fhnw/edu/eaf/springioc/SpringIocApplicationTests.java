package ch.fhnw.edu.eaf.springioc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ch.fhnw.edu.eaf.springioc.provider.HelloWorldProvider;
import ch.fhnw.edu.eaf.springioc.provider.IMessageProvider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringIocApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void helloWorldProvider() {
		BeanFactory factory = SpringIocApplication.getBeanFactory();
		IMessageProvider provider = (IMessageProvider) factory.getBean("provider");

		if (provider instanceof HelloWorldProvider) {
			Assert.assertEquals(provider.getMessage(), "Hello World!");
		} else {
			Assert.fail("Injected provider is not a HelloWorldProvider!");
		}
	}

}
