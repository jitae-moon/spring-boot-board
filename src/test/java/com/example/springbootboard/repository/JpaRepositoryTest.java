package com.example.springbootboard.repository;

import com.example.springbootboard.config.JpaConfig;
import com.example.springbootboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    ArticleRepository articleRepository;
    ArticleCommentRepository articleCommentRepository;

    @Autowired
    public JpaRepositoryTest(ArticleRepository articleRepository, ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Test find")
    @Test
    void givenNothing_whenSearchingArticles_thenReturnsArticles() {
        // Given & When
        long id = 1L;
        Article article = articleRepository.findById(id).orElseThrow();

        // Then
        assertThat(article).isNotNull();
    }

    @DisplayName("Test save")
    @Test
    void givenArticle_whenSavingArticle_thenSavesArticle() {
        // Given
        Article article = Article.of("Test title", "Test content", "Test hashtag");
        long prevCount = articleRepository.count();

        // When
        articleRepository.save(article);

        // Then
        long actual = articleRepository.count();
        assertThat(actual).isEqualTo(prevCount + 1);
    }

    @DisplayName("Test update")
    @Test
    void givenArticle_whenUpdatingArticle_thenUpdatesArticle() {
        // Given
        long id = 1L;
        String newHashtag = "#java";
        Article article = articleRepository.findById(id).orElseThrow();

        // When
        article.setHashtag(newHashtag);

        // Then
        String actual = articleRepository.findById(id).orElseThrow().getHashtag();
        assertThat(actual).isEqualTo(newHashtag);
    }

    @DisplayName("Test delete")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        // Given
        long id = 1L;
        long prevCount = articleRepository.count();

        // When
        articleRepository.deleteById(id);

        // Then
        long actual = articleRepository.count();
        assertThat(actual).isEqualTo(prevCount - 1);
    }

}