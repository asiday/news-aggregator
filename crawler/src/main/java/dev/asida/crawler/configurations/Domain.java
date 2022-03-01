package dev.asida.crawler.configurations;

/**
 * A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com)
 * to restrict the search to.
 *
 * Endpoint: /v2/everything
 */
public class Domain implements Criteria {

    private final String value;

    public Domain(String value) {
        this.value = value;
    }

    @Override
    public String getAbbreviation() {
        return value;
    }


}
