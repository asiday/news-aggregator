package dev.asida.crawler.jpa;

import dev.asida.crawler.dao.ArticleRepository;
import dev.asida.crawler.json.Article;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for CRUD(save, findById, findAll, count, delete) functionality,
 * and one created find method(naming convention).
 */
@SpringBootTest
@Transactional
public class JpaArticleDAOTest {

    private static Logger LOG = LoggerFactory.getLogger(JpaArticleDAOTest.class);

    /**
     * Use Article Repository interface to manage domain classes to map DB tables.
     */
    @Autowired
    private ArticleRepository dao;

    /**
     * Spring Data bean is used to get id's from database.
     */
    @Autowired
    private JdbcTemplate template;

    /**
     * private method to retrieve the current ids in the database
     * @return a list of IDs.
     */
    private List<Integer> getIds() {
        return template
                .query("select id from articles", (rs,num) -> rs.getInt("id"));
    }

    @Test
    public void saveTest() {
        LocalDateTime today = LocalDateTime.now();
        Article article = new Article("author",  "title",  "description",
                 "url",  "urlToImage", today, "content");
        article = dao.save(article);
        assertThat(article.getId(), is(notNullValue()));
    }


    @Test
    public void findOneThatExists() {
        getIds().forEach(id -> {
            Optional<Article> article = dao.findById(id);
            assertTrue(article.isPresent());
            assertThat(article.get().getId(), equalTo(id));
        });
    }

    @Test
    public void findOneThatNotExists() {
        Optional<Article> article = dao.findById(999);
        assertFalse(article.isPresent());
    }

    @Test
    public void countTest() {
        assertEquals(2, dao.count());
    }

    @Test
    public void findAllTest() {
        List<String> dbNames = dao.findAll().stream()
                .map(Article::getAuthor)
                .collect(Collectors.toList());
        assertThat(dbNames, containsInAnyOrder( "Sullivan", "Tyler"));
    }

    @Test
    public void deleteTest() {
        getIds().forEach(id-> {
            Optional<Article> article = dao.findById(id);
            assertTrue(article.isPresent());
            dao.delete(article.get());
        });
        assertEquals(0, dao.count());

    }

    @Test
    public void existsByIdTest() {
        getIds().forEach(id -> assertTrue(dao.existsById(id)));
    }

    @Test
    void findAllByAuthorContainingTest(){
        List<Article> articles= dao.findAllByAuthorContaining("y");
        articles.forEach(article -> LOG.info(article.toString()));
    }
    
}
