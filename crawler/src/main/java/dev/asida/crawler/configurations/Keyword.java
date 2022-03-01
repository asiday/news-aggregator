package dev.asida.crawler.configurations;

/**
 * Keywords or phrases to search for in the article title and body.
 *
 * Advanced search is supported here:
 *
 * Surround phrases with quotes (") for exact match.
 * Prepend words or phrases that must appear with a + symbol. Eg: +bitcoin
 * Prepend words that must not appear with a - symbol. Eg: -bitcoin
 * Alternatively you can use the AND / OR / NOT keywords, and optionally group these with parenthesis. Eg: crypto AND (ethereum OR litecoin) NOT bitcoin.
 * The complete value for q must be URL-encoded. Max length: 500 chars.
 *
 * Endpoint: /v2/everything, /v2/top-headline.
 */
public class Keyword implements Criteria{

    private final String value;

    public Keyword(String value) {
        this.value = value;
    }

    @Override
    public String getAbbreviation() {
        return value;
    }
}
