package com.example.springbootboard.dto;

import com.example.springbootboard.domain.Article;
import com.example.springbootboard.domain.UserAccount;

import java.time.LocalDateTime;

public record ArticleDto(
        String title,
        String content,
        String hashtag,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        UserAccount userAccount
) {

    public static ArticleDto of(String title,
                                String content,
                                String hashtag,
                                String createdBy,
                                LocalDateTime createdAt,
                                String modifiedBy,
                                LocalDateTime modifiedAt,
                                UserAccount userAccount) {
        return new ArticleDto(title, content, hashtag, createdBy, createdAt, modifiedBy, modifiedAt, userAccount);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(article.getTitle(),
                article.getContent(),
                article.getHashtag(),
                article.getCreatedBy(),
                article.getCreatedAt(),
                article.getModifiedBy(),
                article.getModifiedAt(),
                article.getUserAccount());
    }

    public Article toEntity() {
        return Article.of(this.title, this.content, this.hashtag, userAccount);
    }

}
