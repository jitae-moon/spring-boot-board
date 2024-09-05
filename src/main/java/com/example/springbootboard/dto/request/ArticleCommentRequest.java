package com.example.springbootboard.dto.request;

import com.example.springbootboard.dto.ArticleCommentDto;
import com.example.springbootboard.dto.UserAccountDto;

public record ArticleCommentRequest(
        Long articleId,
        String content
) {
    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                content,
                userAccountDto
        );
    }

}
