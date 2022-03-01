package dev.asida.crawler.configurations;

import java.util.Locale;

/**
 * The category you want to get headlines for.
 * Possible options: business, entertainment, general, health, science, sports, technology.
 * Note: you can't mix this param with the sources param.
 *
 * Endpoint: /v2/top-headline
 */
public enum Category implements Criteria{
    BUSINESS, ENTERTAINMENT, GENERAL, HEALTH, SCIENCE, SPORTS, TECHNOLOGY;

    @Override
    public String getAbbreviation() {
        return  this.toString().toLowerCase(Locale.ROOT);
    }
}
