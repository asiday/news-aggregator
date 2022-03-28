package dev.asida.crawler.services;

import dev.asida.crawler.configurations.*;
import dev.asida.crawler.converters.RequestContextConverter;
import dev.asida.crawler.json.Article;
import dev.asida.crawler.json.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NewsServiceTest {

    private NewsService service;
    @Mock
    private RequestContextConverter requestConverter;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        when(builder.build()).thenReturn(restTemplate);
        service = new NewsService(builder, requestConverter, "undefined");
    }

    @Test
    public void getServiceTest() {
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void getAllArticleWithOneParameterTest() {
        // given
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);
        RequestContext r = new RequestContext();
        r.getLanguages().addAll(languages);

        Response response = new Response();
        // Returned response contains a list with one mock article.
        response.setArticles(List.of(new Article()));
        when(requestConverter.convert(eq(r))).thenReturn("language=de");
        when(restTemplate.getForObject(eq("https://newsapi.org/v2/top-headlines?language=de&apiKey=undefined"), eq(Response.class))).thenReturn(response);
        // when
        List<Article> result = service.getAllArticles(r);

        // then
        assertThat(result, is(not(empty())));
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test
    public void getAllArticleWithWrongRequestTest() {
        // given
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);

        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("кириллица"));

        RequestContext r = new RequestContext();
        r.getLanguages().addAll(languages);
        r.getKeywords().addAll(keywords);

        Response response = new Response();
        // Returned response contains a list with one mock article.
        response.setArticles(Collections.emptyList());
        when(requestConverter.convert(eq(r))).thenReturn("language=de&q=кириллица");
        when(restTemplate.getForObject(eq("https://newsapi.org/v2/top-headlines?language=de&q=кириллица&apiKey=undefined"), eq(Response.class))).thenReturn(response);
        // when
        List<Article> result = service.getAllArticles(r);
        // then
        assertThat(result, is(empty()));
    }
}
