package dev.asida.crawler.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Class is a Spring Component that is used for serializing a list of all articles.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private String status;
    private Integer totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }
    public Integer getTotalResults() {
        return totalResults;
    }
    public List<Article> getArticles() {
        return articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }
}
