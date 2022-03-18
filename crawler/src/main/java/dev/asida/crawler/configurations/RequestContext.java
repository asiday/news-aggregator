package dev.asida.crawler.configurations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Configuration class. All paramters are initialised as empty lists.
 * This class is used to create lists of criteria.
 */
public class RequestContext {

    private final List<Country> countries = new ArrayList<>();
    private final List<Domain> domains = new ArrayList<>();
    private final List<Language> languages = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Keyword> keywords = new ArrayList<>();
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public List<Domain> getDomains() {
        return domains;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setDateFrom(LocalDateTime date) {
        this.dateFrom = date;
    }

    public void setDateTo(LocalDateTime date) {
        this.dateTo = date;
    }

    @Override
    public String toString() {
        return "RequestContext{" +
                "countries=" + countries +
                ", domains=" + domains +
                ", languages=" + languages +
                ", categories=" + categories +
                ", from=" + dateFrom +
                ", to=" + dateTo +
                ", q=" + keywords +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestContext that = (RequestContext) o;
        return Objects.equals(countries, that.countries) &&
                Objects.equals(domains, that.domains) &&
                Objects.equals(languages, that.languages) &&
                Objects.equals(categories, that.categories) &&
                Objects.equals(keywords, that.keywords) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countries, domains, languages, categories, keywords, dateFrom, dateTo);
    }
}
