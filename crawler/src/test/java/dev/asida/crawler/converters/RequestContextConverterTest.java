package dev.asida.crawler.converters;

import dev.asida.crawler.configurations.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class RequestContextConverterTest {

    private RequestContextConverter converter = new RequestContextConverter();

    @Test
    public void convertEmptyTest() {
        RequestContext requestContext = new RequestContext();
        String convertedRequestContext = converter.convert(requestContext);
        assertThat(convertedRequestContext, is(emptyString()));
    }

    @Test
    public void convertOneLanguageTest() {
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);

        RequestContext requestContext = new RequestContext();
        requestContext.getLanguages().addAll(languages);

        String result = converter.convert(requestContext);
        String expected = "language=de";

        assertThat(result, equalTo(expected));
    }

    @Test
    public void convertOneCountryTest() {
        List<Country> countries = new ArrayList<>();
        countries.add(Country.SWITZERLAND);

        RequestContext requestContext = new RequestContext();
        requestContext.getCountries().addAll(countries);
        String result = converter.convert(requestContext);
        String expected = "country=ch";
        assertThat(result, equalTo(expected));
    }

    @Test
    public void convertOneCategoryTest() {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.BUSINESS);

        RequestContext requestContext = new RequestContext();
        requestContext.getCategories().addAll(categories);

        String result = converter.convert(requestContext);
        String expected = "category=business";

        assertThat(result, equalTo(expected));
    }

    @Test
    public void convertOneDomainTest() {
        List<Domain> domains = new ArrayList<>();
        domains.add(new Domain("lenta.ru"));

        RequestContext requestContext = new RequestContext();
        requestContext.getDomains().addAll(domains);

        String result = converter.convert(requestContext);
        String expected = "domains=lenta.ru";

        assertThat(result, equalTo(expected));
    }

    @Test
    public void convertDomainWithSlashTest() {
        List<Domain> domains = new ArrayList<>();
        domains.add(new Domain("srf.ch/news"));

        RequestContext requestContext = new RequestContext();
        requestContext.getDomains().addAll(domains);

        String result = converter.convert(requestContext);
        String expected = "domains=srf.ch/news";

        assertThat(result, equalTo(expected));
    }

    @Test
    public void convertDateFromTest() {
        LocalDateTime localDate = LocalDateTime.now().minusDays(1);

        RequestContext requestContext = new RequestContext();
        requestContext.setDateFrom(localDate);

        String result = converter.convert(requestContext);
        String expected = "from=" + localDate;

        assertThat(result, equalTo(expected));
    }

    @Test
    public void convertDateToTest() {
        LocalDateTime localDate = LocalDateTime.now();

        RequestContext requestContext = new RequestContext();
        requestContext.setDateTo(localDate);

        String result = converter.convert(requestContext);
        String expected = "to=" + localDate;

        assertThat(result, equalTo(expected));
    }

    @Test
    public void convertKeywordTest() {
        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("Russland"));

        RequestContext requestContext = new RequestContext();
        requestContext.getKeywords().addAll(keywords);

        String result = converter.convert(requestContext);
        String expected = "q=Russland";

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void convertCountriesTest() {
        List<Country> countries = new ArrayList<>();
        countries.add(Country.SWITZERLAND);
        countries.add(Country.GERMANY);
        countries.add(Country.RUSSIA);

        RequestContext requestContext = new RequestContext();
        requestContext.getCountries().addAll(countries);

        String result = converter.convert(requestContext);
        String expected = "country=ch,de,ru";

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void convertLanguagesTest() {
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);
        languages.add(Language.RUSSIAN);
        languages.add(Language.FRENCH);

        RequestContext requestContext = new RequestContext();
        requestContext.getLanguages().addAll(languages);

        String result = converter.convert(requestContext);
        String expected = "language=de,fr,ru";

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void convertCategoriesTest() {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.BUSINESS);
        categories.add(Category.HEALTH);
        categories.add(Category.SCIENCE);

        RequestContext requestContext = new RequestContext();
        requestContext.getCategories().addAll(categories);

        String result = converter.convert(requestContext);
        String expected = "category=business,health,science";

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void convertDomainsTest() {
        List<Domain> domains = new ArrayList<>();
        domains.add(new Domain("lenta.ru"));
        domains.add(new Domain("srf.ch/news"));
        domains.add(new Domain("zeit.de"));

        RequestContext requestContext = new RequestContext();
        requestContext.getDomains().addAll(domains);

        String result = converter.convert(requestContext);
        String expected = "domains=lenta.ru,srf.ch/news,zeit.de";

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void convertTwoTest() {
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);
        languages.add(Language.FRENCH);
        languages.add(Language.RUSSIAN);

        List<Country> countries = new ArrayList<>();
        countries.add(Country.SWITZERLAND);

        RequestContext requestContext = new RequestContext();
        requestContext.getCountries().addAll(countries);
        requestContext.getLanguages().addAll(languages);

        String result = converter.convert(requestContext);
        String expected = "country=ch&language=de,fr,ru";

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void convertAllTest() {
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);
        languages.add(Language.FRENCH);
        languages.add(Language.RUSSIAN);

        List<Country> countries = new ArrayList<>();
        countries.add(Country.SWITZERLAND);

        List<Category> categories = new ArrayList<>();
        categories.add(Category.BUSINESS);

        List<Domain> domains = new ArrayList<>();
        domains.add(new Domain("srf.ch/news"));

        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("Russland"));

        RequestContext requestContext = new RequestContext();
        requestContext.getCountries().addAll(countries);
        requestContext.getCategories().addAll(categories);
        requestContext.getDomains().addAll(domains);
        requestContext.getLanguages().addAll(languages);
        requestContext.getKeywords().addAll(keywords);

        LocalDateTime localDate = LocalDateTime.now().minusDays(1);
        requestContext.setDateFrom(localDate);
        String result = converter.convert(requestContext);
        String expected = "category=business&" +
                "country=ch&" +
                "domains=srf.ch/news&" +
                "from=" + localDate +
                "&language=de,fr,ru&" +
                "q=Russland";

        assertThat(result, is(equalTo(expected)));
    }

}
