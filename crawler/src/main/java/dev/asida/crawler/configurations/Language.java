package dev.asida.crawler.configurations;
/**
 * The 2-letter ISO-639-1 code of the language you want to get headlines for.
 * Possible options: see at newsapi.com.
 *
 * Default: all languages returned.
 * Endpoint: /v2/everything.
 */
public enum Language implements Criteria{
    GERMAN("de"),
    ENGLISH("en"),
    FRENCH("fr"),
    RUSSIAN("ru");

    private String abbreviation;

    Language(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String getAbbreviation(){
        return abbreviation;
    }
}
