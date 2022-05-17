package dev.asida.crawler.converters;

import dev.asida.crawler.configurations.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestContextValidator {

    private static final Logger LOG = LoggerFactory.getLogger(RequestContextValidator.class);
    // url must have keyword/list of keywords or domain/list of domains.

    public boolean validate(RequestContext rc) {
        boolean marker = false;
        if (!rc.getCountries().isEmpty() || !rc.getCategories().isEmpty()) {
            marker = false;
            LOG.info("The category or country param is not currently supported on the /everything endpoint.");
        }
        if (!rc.getDomains().isEmpty() || !rc.getKeywords().isEmpty()) {
            marker = true;
        } else
            LOG.info("Required parameters are missing. Please set: keyword or domains.");
        return marker;
    }
}
