package com.example.springbootboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String getArticles(Model model) {
        model.addAttribute("articles", "");

        return "articles/index";
    }

    @GetMapping("{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", "");

        return "articles/detail";
    }

}
