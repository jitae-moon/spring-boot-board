package com.example.springbootboard.dto.response;

import com.example.springbootboard.dto.ArticleDto;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        String email,
        String nickname,
        LocalDateTime createdAt
) {
    public static ArticleResponse of(Long id, String title, String content, String hashtag, String email, String nickname, LocalDateTime createdAt) {
        return new ArticleResponse(id, title, content, hashtag, email, nickname, createdAt);
    }

    public static ArticleResponse from(ArticleDto dto) {
        String nickname = dto.userAccountDto().nickname();

        if (ObjectUtils.isEmpty(nickname)) nickname = dto.userAccountDto().userId();

        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.userAccountDto().email(),
                nickname,
                dto.createdAt()
        );
    }
}
