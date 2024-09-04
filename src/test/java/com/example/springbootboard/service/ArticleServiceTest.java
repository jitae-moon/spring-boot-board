package com.example.springbootboard.service;

import com.example.springbootboard.dto.ArticleDto;
import com.example.springbootboard.repository.article.ArticleRepository;
import com.example.springbootboard.service.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    ArticleService sut;

    @Mock
    ArticleRepository articleRepository;

    @Test
    void givenNoSearchParams_whenSearchingArticlesWithHashtag_thenReturnsEmptyPage() {
        // Given
        Pageable pageable = Pageable.ofSize(20);

        // When
        Page<ArticleDto> articles = sut.getArticlesWithHashtag(null, pageable);

        // Then
        assertThat(articles).isEqualTo(Page.empty(pageable));
        then(articleRepository).shouldHaveNoInteractions();
    }

    @Test
    void givenSearchParams_whenSearchingArticlesWithHashtag_thenReturnsArticlesPage() {
        // Given
        String hashtag = "#java";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByHashtag(eq(hashtag), eq(pageable)))
                .willReturn(Page.empty(pageable));

        // When
        Page<ArticleDto> articles = sut.getArticlesWithHashtag(hashtag, pageable);

        // Then
        assertThat(articles).isEqualTo(Page.empty(pageable));
        then(articleRepository).should().findByHashtag(eq(hashtag), eq(pageable));
    }

    @Test
    void givenNothing_whenSearchingHashtags_thenReturnsUniqueHashtags() {
        // Given
        List<String> expected = List.of("#java", "#spring", "#c++");
        given(articleRepository.findAllDistinctHashtags()).willReturn(expected);

        // When
        List<String> actual = sut.getDistinctHashtags();

        // Then
        assertThat(actual).isEqualTo(expected);
        then(articleRepository).should().findAllDistinctHashtags();
    }

}
