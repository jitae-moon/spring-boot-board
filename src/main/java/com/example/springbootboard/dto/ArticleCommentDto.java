package com.example.springbootboard.dto;

import com.example.springbootboard.domain.Article;
import com.example.springbootboard.domain.ArticleComment;
import com.example.springbootboard.domain.UserAccount;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        String content,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        Article article,
        UserAccount userAccount
) {
    public static ArticleCommentDto of(String content, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, Article article, UserAccount userAccount) {
        return new ArticleCommentDto(content, createdBy, createdAt, modifiedBy, modifiedAt, article, userAccount);
    }

    public ArticleCommentDto from(ArticleComment articleComment) {
        return new ArticleCommentDto(articleComment.getContent(),
                articleComment.getCreatedBy(),
                articleComment.getCreatedAt(),
                articleComment.getModifiedBy(),
                articleComment.getModifiedAt(),
                articleComment.getArticle(),
                articleComment.getUserAccount()
                );
    }

    public ArticleComment toEntity() {
        return ArticleComment.of(this.content, this.article, this.userAccount);
    }

}
