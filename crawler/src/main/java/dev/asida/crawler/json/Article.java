package dev.asida.crawler.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
/**
 * Class is a Spring Component that is used for serializing a one news article.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    private String title;
    private String description;
    private String url;
    private LocalDate publishedAt;

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
