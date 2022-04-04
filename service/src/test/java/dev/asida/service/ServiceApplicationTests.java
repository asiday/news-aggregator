package dev.asida.service;

import dev.asida.service.data.Article;
import dev.asida.service.repositories.ArticleRepository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ServiceApplicationTests {
    @Autowired
    private ArticleRepository repository;
    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Article> articles = repository.findAll();
        Assertions.assertEquals(2, articles.size());
    }
}