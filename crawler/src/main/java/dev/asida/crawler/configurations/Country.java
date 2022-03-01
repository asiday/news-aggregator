package dev.asida.crawler.configurations;

/**
 * The 2-letter ISO 3166-1 code of the country you want to get headlines for.
 * Possible options: see at newsapi.com.
 * Note: you can't mix this param with the sources param.
 *
 * Endpoint: /v2/top-headline
 */
public enum Country implements Criteria {
    RUSSIA("ru"),
    UNATEDSTATES("us"),
    SWITZERLAND("ch"),
    BULGARIA("bg"),
    GERMANY("de");

    private String abbreviation;

    Country(String abbreviation) {
        this.abbreviation = abbreviation;
    }


    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

}


