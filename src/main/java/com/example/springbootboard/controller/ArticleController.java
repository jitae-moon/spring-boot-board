package com.example.springbootboard.controller;

import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String getArticles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        log.debug("ArticleController :: getArticles");
        model.addAttribute("articles", articleService.getArticles(searchType, searchValue, pageable)); // TODO: Change to ArticleResponseDto

        return "articles/index";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        log.debug("ArticleController :: getArticle :: id = {}", id);
        model.addAttribute("article", articleService.getArticle(id)); // TODO: Change to ArticleWithCommentsResponseDto

        return "articles/detail";
    }

}
