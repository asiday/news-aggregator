package dev.asida.crawler.converters;

import dev.asida.crawler.configurations.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * News API has 2 main endpoints: everything and top-headlines.
 * Here we are testing only everything.
 * Request parameters for "everything":
 * Keyword: The complete value for q must be URL-encoded. Max length: 500 chars.
 * Domains A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.
 * From: A date and optional time for the oldest article allowed.
 * To: A date and optional time for the newest article allowed.
 * Language: The 2-letter ISO-639-1 code of the language you want to get headlines for.
 * Request parameters for "top-headlines":
 * country
 * The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options: aearataubebgbrcachcncocuczdeegfrgbgrhkhuidieilinitjpkrltlvmamxmyngnlnonzphplptrorsrusasesgsiskthtrtwuausveza. Note: you can't mix this param with the sources param.
 * category
 * The category you want to get headlines for. Possible options: businessentertainmentgeneralhealthsciencesportstechnology. Note: you can't mix this param with the sources param.
 * q
 * Keywords or a phrase to search for.
 */

@SpringBootTest
public class RequestContextValidatorTest {

    private RequestContextValidator validator = new RequestContextValidator();

    /**
     * Request parameters for "everything":
     * Keyword
     * The complete value for q must be URL-encoded. Max length: 500 chars.
     * domains
     * A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.
     * from
     * A date and optional time for the oldest article allowed. This should be in ISO 8601 format (e.g. 2022-05-16 or 2022-05-16T09:54:35)
     * to
     * A date and optional time for the newest article allowed. This should be in ISO 8601 format (e.g. 2022-05-16 or 2022-05-16T09:54:35)
     * language
     * The 2-letter ISO-639-1 code of the language you want to get headlines for. Possible options: ardeenesfrheitnlnoptrusvudzh.
     *
     * Request parameters for "top-headlines":
     * country
     * The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options: aearataubebgbrcachcncocuczdeegfrgbgrhkhuidieilinitjpkrltlvmamxmyngnlnonzphplptrorsrusasesgsiskthtrtwuausveza. Note: you can't mix this param with the sources param.
     * category
     * The category you want to get headlines for. Possible options: businessentertainmentgeneralhealthsciencesportstechnology. Note: you can't mix this param with the sources param.
     * q
     * Keywords or a phrase to search for.
     */
    @Test
    public void validateEmptyTest() {
        RequestContext requestContext = new RequestContext();

        boolean validatedRequestContext= validator.validate(requestContext);
        assertThat(validatedRequestContext, is(false));
    }


    @Test
    public void validateEverythingKeywordTest() {
        List<Keyword> keywords = new ArrayList<>();
        keywords.add(new Keyword("apple"));

        RequestContext requestContext = new RequestContext();
        requestContext.getKeywords().addAll(keywords);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(true));
    }
    // TODO test: Can I add more then one keyword?
    // TODO test: Test that keyword not longer then 500 symbols.


    @Test
    public void validateEverythingDomainTest() {
        List<Domain> domains = new ArrayList<>();
        domains.add(new Domain("bbc.co.uk"));

        RequestContext requestContext = new RequestContext();
        requestContext.getDomains().addAll(domains);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(true));
    }
    @Test
    public void validateEverythingDomainsTest() {
        List<Domain> domains = new ArrayList<>();
        domains.add(new Domain("bbc.co.uk"));
        domains.add(new Domain("techcrunch.com"));
        domains.add(new Domain("engadget.com"));

        RequestContext requestContext = new RequestContext();
        requestContext.getDomains().addAll(domains);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(true));
    }

    @Test
   public void validateEverythingFromTest() {
        LocalDateTime localDate = LocalDateTime.now().minusDays(1);

        RequestContext requestContext = new RequestContext();
        requestContext.setDateFrom(localDate);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(false));
    }

    @Test
    public void validateEverythingToTest() {
        LocalDateTime localDate = LocalDateTime.now();

        RequestContext requestContext = new RequestContext();
        requestContext.setDateTo(localDate);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(false));
    }
    // TODO test: DateFrom is earlier as DateTo.

    @Test
    public void validateEverythingFromToTest() {
        LocalDateTime localDateFrom = LocalDateTime.now().minusDays(1);
        LocalDateTime localDateTo = LocalDateTime.now();

        RequestContext requestContext = new RequestContext();
        requestContext.setDateFrom(localDateFrom);
        requestContext.setDateTo(localDateTo);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(false));
    }

    @Test
   public void validateEverythingLanguageTest() {
        List<Language> languages = new ArrayList<>();
        languages.add(Language.GERMAN);

        RequestContext requestContext = new RequestContext();
        requestContext.getLanguages().addAll(languages);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(false));
    }

    @Test
   public void validateEverythingCountryTest() {
        List<Country> countries = new ArrayList<>();
        countries.add(Country.RUSSIA);

        RequestContext requestContext = new RequestContext();
        requestContext.getCountries().addAll(countries);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(false));
    }

    @Test
   public void validateEverythingCategoryTest() {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.BUSINESS);

        RequestContext requestContext = new RequestContext();
        requestContext.getCategories().addAll(categories);

        boolean result = validator.validate(requestContext);

        assertThat(result, equalTo(false));
    }


}
