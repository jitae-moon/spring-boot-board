package com.example.springbootboard.dto;

import com.example.springbootboard.domain.Article;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleWithCommentsDto(
        Long id,
        String title,
        String content,
        String hashtag,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        Set<ArticleCommentDto> articleCommentDtos,
        UserAccountDto userAccountDto
) {
    public static ArticleWithCommentsDto of(Long id, String title, String content, String hashtag, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt, Set<ArticleCommentDto> articleCommentDtos, UserAccountDto userAccountDto) {
        return new ArticleWithCommentsDto(id, title, content, hashtag, createdBy, createdAt, modifiedBy, modifiedAt, articleCommentDtos, userAccountDto);
    }

    public static ArticleWithCommentsDto from(Article article) {
        return new ArticleWithCommentsDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getHashtag(),
                article.getCreatedBy(),
                article.getCreatedAt(),
                article.getModifiedBy(),
                article.getModifiedAt(),
                article.getArticleComments().stream().map(ArticleCommentDto::from).collect(Collectors.toCollection(LinkedHashSet::new)),
                UserAccountDto.of(
                        article.getUserAccount().getUserId(),
                        article.getUserAccount().getPassword(),
                        article.getUserAccount().getEmail(),
                        article.getUserAccount().getNickname(),
                        article.getUserAccount().getCreatedBy(),
                        article.getUserAccount().getCreatedAt(),
                        article.getUserAccount().getModifiedBy(),
                        article.getUserAccount().getModifiedAt()
                )
        );
    }
}
