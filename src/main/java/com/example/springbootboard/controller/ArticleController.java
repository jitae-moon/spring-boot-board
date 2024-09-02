package com.example.springbootboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String getArticles(Model model) {
        log.debug("ArticleController :: getArticles");
//        model.addAttribute("articles", List.of(Article.of("test title", "test content", "test hashtag")));

        return "articles/index";
    }

    @GetMapping("{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        log.debug("ArticleController :: getArticle :: id = {}", id);
//        model.addAttribute("article", Article.of("test title", "test content", "test hashtag"));

        return "articles/detail";
    }

}
