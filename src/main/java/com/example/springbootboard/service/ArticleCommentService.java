package com.example.springbootboard.service;

import com.example.springbootboard.dto.ArticleCommentDto;
import com.example.springbootboard.repository.ArticleCommentRepository;
import com.example.springbootboard.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ArticleCommentService {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public ArticleCommentService(ArticleRepository articleRepository, ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long id) {
        return null;
    }
}
