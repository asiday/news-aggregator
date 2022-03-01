package dev.asida.crawler.converters;

import dev.asida.crawler.configurations.*;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Used to convert existing request context to a string with Name of GET request parameter and a list of all parameters.
 */
@Component
public class RequestContextConverter {

    /**
     * Takes a request parameter and creates a string that can be inserted in GET request.
     * @param requestContext contains criteria in specific object class format
     * @return a String with context parameters. Each group of criteria are separated with "&".
     *         and each parameter within a group is separeted with ",".
     */
    public String convert(RequestContext requestContext) {
        List<String> listOfCriteria = new ArrayList<>();
        if (!requestContext.getCountries().isEmpty()) {
            listOfCriteria.add("country=" + createCriteriaString(requestContext.getCountries()));
        }
        if (!requestContext.getDomains().isEmpty()) {
            listOfCriteria.add("domains=" + createCriteriaString(requestContext.getDomains()));
        }
        if (!requestContext.getLanguages().isEmpty()) {
            listOfCriteria.add("language=" + createCriteriaString(requestContext.getLanguages()));
        }
        if (!requestContext.getCategories().isEmpty()) {
            listOfCriteria.add("category=" + createCriteriaString(requestContext.getCategories()));
        }
        if (!requestContext.getKeywords().isEmpty()) {
            listOfCriteria.add("q=" + createCriteriaString(requestContext.getKeywords()));
        }
        if (requestContext.getDateFrom() != null){
            listOfCriteria.add("from=" + requestContext.getDateFrom());
        }
        if (requestContext.getDateTo() != null){
            listOfCriteria.add("to=" + requestContext.getDateTo());
        }

        List<String> sortedListOfCriteria = listOfCriteria.stream().sorted().collect(Collectors.toList());
        return String.join("&", sortedListOfCriteria);
    }

    /**
     * Takes a list of criteria and creates one String with all criteria
     * @param myParams is a list of parameters.
     * @param <T> means that is implement Criteria interface.
     * @return a string of parameters
     */
    private <T extends Criteria> String createCriteriaString(List<T> myParams) {
        return myParams
                .stream()
                .map(Criteria::getAbbreviation)
                .sorted()
                .collect(Collectors.joining(","));
    }
}
