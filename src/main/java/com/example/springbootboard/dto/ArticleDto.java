package com.example.springbootboard.dto;

import com.example.springbootboard.domain.Article;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        String title,
        String content,
        String hashtag,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        UserAccountDto userAccountDto
) {

    public static ArticleDto of(Long id,
                                String title,
                                String content,
                                String hashtag,
                                String createdBy,
                                LocalDateTime createdAt,
                                String modifiedBy,
                                LocalDateTime modifiedAt,
                                UserAccountDto userAccountDto) {
        return new ArticleDto(id, title, content, hashtag, createdBy, createdAt, modifiedBy, modifiedAt, userAccountDto);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getHashtag(),
                article.getCreatedBy(),
                article.getCreatedAt(),
                article.getModifiedBy(),
                article.getModifiedAt(),
                UserAccountDto.from(article.getUserAccount())
                );
    }

    public Article toEntity() {
        return Article.of(this.title, this.content, this.hashtag, userAccountDto.toEntity());
    }

}
