package com.example.springbootboard.dto;

import com.example.springbootboard.domain.Article;
import com.example.springbootboard.domain.ArticleComment;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        String content,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        Article article,
        UserAccountDto userAccountDto
) {
    public static ArticleCommentDto of(Long id, String content, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, Article article, UserAccountDto userAccountDto) {
        return new ArticleCommentDto(id, content, createdBy, createdAt, modifiedBy, modifiedAt, article, userAccountDto);
    }

    public static ArticleCommentDto of(Long articleId, String content, UserAccountDto userAccountDto) {
        return new ArticleCommentDto(articleId, content, null, null, null, null, null, userAccountDto);
    }

    public static ArticleCommentDto from(ArticleComment articleComment) {
        return new ArticleCommentDto(
                articleComment.getId(),
                articleComment.getContent(),
                articleComment.getCreatedBy(),
                articleComment.getCreatedAt(),
                articleComment.getModifiedBy(),
                articleComment.getModifiedAt(),
                articleComment.getArticle(),
                UserAccountDto.from(articleComment.getUserAccount())
                );
    }

    public ArticleComment toEntity() {
        return ArticleComment.of(this.content, this.article, userAccountDto.toEntity());
    }

}
