package com.example.springbootboard.controller;

import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.dto.response.ArticleResponse;
import com.example.springbootboard.dto.response.ArticleWithCommentsResponse;
import com.example.springbootboard.service.article.ArticleService;
import com.example.springbootboard.service.article.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String getArticles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        log.info("ArticleController :: getArticles :: searchType = {}, searchValue = {}, pageable = {}", searchType, searchValue, pageable);
        Page<ArticleResponse> articles = articleService.getArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        model.addAttribute("articles", articles);
        model.addAttribute("searchTypes", SearchType.values());
        model.addAttribute("paginationBarNumbers", paginationBarNumbers);

        return "articles/index";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        log.info("ArticleController :: getArticle :: id = {}", id);
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(id));
        model.addAttribute("article", article);
        model.addAttribute("articleComments", article.articleCommentResponses());

        return "articles/detail";
    }

    @GetMapping("/search-hashtag")
    public String getHashtags(
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        log.info("ArticleController :: getHashtags :: searchValue = {}, pageable = {}", searchValue, pageable);
        Page<ArticleResponse> articlesWithHashtag = articleService.getArticlesWithHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<String> hashtags = articleService.getDistinctHashtags();
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articlesWithHashtag.getTotalPages());

        model.addAttribute("articles", articlesWithHashtag);
        model.addAttribute("hashtags", hashtags);
        model.addAttribute("searchType", SearchType.HASHTAG);
        model.addAttribute("paginationBarNumbers", paginationBarNumbers);

        return "articles/search-hashtag";
    }

}
