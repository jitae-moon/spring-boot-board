package com.example.springbootboard.service;

import com.example.springbootboard.domain.UserAccount;
import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.dto.ArticleDto;
import com.example.springbootboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchValue, Pageable pageable) {
        if (ObjectUtils.isEmpty(searchValue)) return articleRepository.findAll(pageable).map(ArticleDto::from);

//        return switch (searchType) {
//            case TITLE ->
//        }

        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(Long id) {
        return ArticleDto.of("title", "content", "#java", "admin", LocalDateTime.now(), "admin", LocalDateTime.now(), UserAccount.of("userId", "password", "email", "nickname"));
    }

    public void saveArticle(ArticleDto dto) {

    }

    public void updateArticle(Long id, ArticleDto dto) {

    }

    public void deleteArticle(Long id) {

    }

}
