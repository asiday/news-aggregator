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
    /**
     * Create your "dynamic finder" using certain naming convention.
     * Here it looks for a Author name, that contains parameter "string".
     * @param string is a letter or part of a string that is a part of the Author name.
     * @return A list of Articles, that contains this "string" in Author name.
     */
    List<Article> findAllByAuthorContaining(String string);
}
