package dev.asida.crawler.dao;

import dev.asida.crawler.json.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This is Data Access Interface.
 * The CrudRepository of JpaRepository interface provides
 * sophisticated CRUD(create, update, delete) functionality:
 * save, findById, FindAll, count, delete.
 * Implementation is done under the hood by Spring Data.
 * You can make your own "dynamic finder" -
 * create your own queries using certain naming convention.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
