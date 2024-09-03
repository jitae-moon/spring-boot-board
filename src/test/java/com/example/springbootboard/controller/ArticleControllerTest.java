package com.example.springbootboard.controller;

import com.example.springbootboard.config.SecurityConfig;
import com.example.springbootboard.domain.type.SearchType;
import com.example.springbootboard.dto.ArticleWithCommentsDto;
import com.example.springbootboard.dto.UserAccountDto;
import com.example.springbootboard.service.ArticleService;
import com.example.springbootboard.service.PaginationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View controller - Article")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    MockMvc mvc;

    @MockBean
    ArticleService articleService;

    @MockBean
    PaginationService paginationService;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[VIEW][GET] - List of articles")
    @Test
    void givenNothing_whenSearchingArticles_thenReturnsArticlesView() throws Exception {
        // Given
        given(articleService.getArticles(eq(null), eq(null), any(Pageable.class)))
                .willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt()))
                .willReturn(List.of(0, 1, 2, 3, 4));

        // When & Then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("paginationBarNumbers"));

        then(articleService).should().getArticles(eq(null), eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[VIEW][GET] - List of articles with search parameters")
    @Test
    void givenSearchParams_whenSearchingArticles_thenReturnsArticlesView() throws Exception {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchValue = "title";
        given(articleService.getArticles(eq(searchType), eq(searchValue), any(Pageable.class)))
                .willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt()))
                .willReturn(List.of(0, 1, 2, 3, 4));

        // When & Then
        mvc.perform(get("/articles")
                        .queryParam("searchType", searchType.name())
                        .queryParam("searchValue", searchValue))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                        .andExpect(model().attributeExists("searchTypes"));

        then(articleService).should().getArticles(eq(searchType), eq(searchValue), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[VIEW][GET] - Detail view of article")
    @Test
    void givenNothing_whenSearchingArticle_thenReturnsArticleDetail() throws Exception {
        // Given
        Long id = 1L;
        given(articleService.getArticle(id))
                .willReturn(createArticleWithCommentsDto());

        // When & Then
        mvc.perform(get("/articles/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("article"))
                .andExpect(view().name("articles/detail"));

        then(articleService).should().getArticle(id);
    }

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                "Test title",
                "Test content",
                "#java",
                "admin",
                LocalDateTime.now(),
                "admin",
                LocalDateTime.now(),
                Set.of(),
                createUserAccountDto()
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "adminId",
                "1234",
                "test@test.com",
                "adminNick",
                "admin",
                LocalDateTime.now(),
                "adminNick",
                LocalDateTime.now()
        );
    }

}
