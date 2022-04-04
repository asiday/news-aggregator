package dev.asida.service.repositories;

import dev.asida.service.data.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Here Spring Data JPA is to handle the tedious database interactions.
 * By simply declaring the following ArticleRepository interface
 * we automatically will be able to Create, Update, Delete and
 * Find Articles (one, all, or search by simple or complex properties)
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
