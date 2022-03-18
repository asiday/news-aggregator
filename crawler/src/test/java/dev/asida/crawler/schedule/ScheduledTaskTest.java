package dev.asida.crawler.schedule;

import dev.asida.crawler.configurations.Language;
import dev.asida.crawler.configurations.RequestContext;
import dev.asida.crawler.services.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class ScheduledTaskTest {

    @Mock
    NewsService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void requestScheduled() {
        // given
        RequestContext r = new RequestContext();
        r.getLanguages().add(Language.GERMAN);
        ScheduledTask task = new ScheduledTask(service);

        // when
        task.requestArticles();

        // then
        verify(service).getAllArticles(eq(r));
    }

}
