package dev.asida.crawler.services;

import dev.asida.crawler.configurations.*;
import dev.asida.crawler.json.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class NewsServiceTest {

    @Autowired
    private NewsService service;

    @Test
    public void  getServiceTest(){
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void getAllArticleWithOneParameterTest(){
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);

        RequestContext r = new RequestContext();
        r.getLanguages().addAll(languages);

        List<Article> result = service.getAllArticles(r);
        assertThat(result, is(not(empty())));
    }

    @Test
    public void getAllArticlesWithCorrectRequestTest(){
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);

        List<Country> countries = new ArrayList<>();
        countries.add(Country.SWITZERLAND);

        List<Category> categories = new ArrayList<>();
        categories.add(Category.BUSINESS);

        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("Russland"));

        LocalDateTime date = LocalDateTime.now().minusDays(1);

        RequestContext r = new RequestContext();
        r.getCountries().addAll(countries);
        r.getLanguages().addAll(languages);
        r.getKeywords().addAll(keywords);
        r.setDateFrom(date);

        List<Article> result = service.getAllArticles(r);
        assertThat(result, is(not(empty())));
    }

    @Test
    public void getAllArticleWithWrongRequestTest(){
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);

        List<Country> countries = new ArrayList<>();
        countries.add(Country.RUSSIA);

        List<Category> categories = new ArrayList<>();
        categories.add(Category.BUSINESS);

        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("Russland"));

        List<Domain> domains = new ArrayList<>();
        domains.add(new Domain("lenta.ru"));

        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime today = LocalDateTime.now();

        RequestContext r = new RequestContext();
        r.getCountries().addAll(countries);
        r.getLanguages().addAll(languages);
        r.getKeywords().addAll(keywords);
        r.getDomains().addAll(domains);
        r.setDateFrom(yesterday);
        r.setDateTo(today);

        List<Article> result = service.getAllArticles(r);
        assertThat(result, is(empty()));
    }
}
