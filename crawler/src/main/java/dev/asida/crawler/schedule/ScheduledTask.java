package dev.asida.crawler.schedule;

import dev.asida.crawler.configurations.Language;
import dev.asida.crawler.configurations.RequestContext;
import dev.asida.crawler.json.Article;
import dev.asida.crawler.services.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The "Scheduled" annotation defines when a particular method runs.
 * This example uses "fixedRate", which specifies the interval between method invocations,
 * measured from the start time of each invocation.
 * The task will be executed the first time after the initialDelay value = 5 seconds (5000),
 * and it will continue to be executed according to the fixedDelay = 15 minutes (900000).
 */
@Component
public class ScheduledTask {

    public static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);
    private final NewsService service;

    @Autowired
    public ScheduledTask(NewsService service) {
        this.service = service;
    }

    @Scheduled( fixedDelayString = "${schedulerDelay.in.milliseconds:900000}",
                initialDelayString = "${initialDelay.in.milliseconds:5000}")
    public void requestArticles() {
        RequestContext r = new RequestContext();
        r.getLanguages().add(Language.GERMAN);

        List<Article> articles = service.getAllArticles(r);

        LOG.info("The number of retrieved Articles: {}", articles.size());
    }
}
