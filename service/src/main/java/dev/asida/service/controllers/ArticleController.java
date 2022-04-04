package dev.asida.service.controllers;

import dev.asida.service.data.Article;
import dev.asida.service.repositories.ArticleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Here we wrap the repository with a web layer using Spring MVC.
 * @RestController indicates that the data returned by each method
 * will be written straight into the response body instead of rendering a template.
 * An ArticleRepository is injected by constructor into the controller.
 * @RestController : Combination of @Controller and @ResponseBody. Beans
 * are returned are converted to/from JSON/XML.
 *
 */
@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    public List<Article> all() {
        return articleRepository.findAll();
    }
}
