package dev.asida.crawler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CrawlerApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(context);
		int count = context.getBeanDefinitionCount();
		System.out.println(count);

	}

}
