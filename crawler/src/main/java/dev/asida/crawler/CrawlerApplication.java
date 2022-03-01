package dev.asida.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application is created to collect/save/show news.
 * For that purpose is an API newsapi.org is used.
 * Application has two main services:
 * <ul>
 * <li>Crawler service.
 * <li>RestFull service.
 * <ul/>
 * <p>
 * Crawler Service is retrieving news articles,
 * according to the schedule from different sources using multiple criteria.
 * <p>
 * RestFull Service is working with database. Saving, updating and providing news articles.
 *
 * @author Asida Yambay
 *
 */

@SpringBootApplication
public class CrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlerApplication.class, args);
	}

}
