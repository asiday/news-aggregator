package dev.asida.crawler.services;

import dev.asida.crawler.configurations.*;

import dev.asida.crawler.converters.RequestContextConverter;
import dev.asida.crawler.json.Article;
import dev.asida.crawler.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * Using the RestTemplateBuilder of SpringBoot it creates a connection to the api and sends a GET request.
 * All criteria for this request are manipulated using RequestContext class.
 * Server receives a JSON data as a part of HTTP request
 * <p>
 * restTemplate - is a component of Spring Framework that is using InterceptingHttpAccessor to access api.
 * <p>
 * key - is saved in resources/application.properties
 */
@Service
public class NewsService {

    private final RestTemplate restTemplate;
    private final RequestContextConverter converter;
    private String key;

    @Autowired
    public NewsService(RestTemplateBuilder builder, RequestContextConverter converter, @Value("${key:undefined}") String key) {
        this.restTemplate = builder.build();
        this.converter = converter;
        this.key = key;
    }

    /**
     * Sends a Get request using all possible search criteria.
     * @param r is a request Parameter with List of all requests in list form..
     * @return a List of Articles. Each article has a JSON type.
     */
    public List<Article> getAllArticles(RequestContext r) {
        String context = converter.convert(r);
        String url = String.format("https://newsapi.org/v2/top-headlines?%s&apiKey=%s", context, key);
        Response response = restTemplate.getForObject(url, Response.class);
        return response.getArticles();
    }
}
