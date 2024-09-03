package com.example.springbootboard.service;

import com.example.springbootboard.domain.Article;
import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.dto.ArticleDto;
import com.example.springbootboard.dto.ArticleWithCommentsDto;
import com.example.springbootboard.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> getArticles(SearchType searchType, String searchValue, Pageable pageable) {
        if (ObjectUtils.isEmpty(searchValue)) return articleRepository.findAll(pageable).map(ArticleDto::from);

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchValue, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchValue, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchValue, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchValue, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchValue, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long id) {
        return articleRepository.findById(id)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("No article found - article id = {}" + id));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(Long id, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(id);
            if (dto.title() != null) article.setTitle(dto.title());
            if (dto.content() != null) article.setTitle(dto.content());
            article.setHashtag(dto.hashtag());
        } catch (EntityNotFoundException e) {
            log.error("Cannot find article - dto = {}", dto);
        }
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

}
