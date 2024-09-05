package com.example.springbootboard.controller;

import com.example.springbootboard.dto.request.ArticleCommentRequest;
import com.example.springbootboard.service.article.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        log.info("ArticleCommentController :: postNewArticleComment");

        // TODO: authentication to inform user info

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id, Long articleId) {
        log.info("ArticleCommentController :: deleteArticle :: id = {}", id);

        return "redirect:/articles/" + articleId;
    }

}
