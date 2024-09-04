package com.example.springbootboard.service;

import com.example.springbootboard.service.article.PaginationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Business logic - Pagination")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class) // Lighten test environment
class PaginationServiceTest {

    @Autowired
    PaginationService sut;

    @MethodSource
    @ParameterizedTest
    void givenCurrentPageNumberAndTotalPages_whenCalculatingPageNumberBars_thenReturnsPaginationBarNumbersToList(int currentPageNumber, int totalPages, List<Integer> expected) {
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPages);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> givenCurrentPageNumberAndTotalPages_whenCalculatingPageNumberBars_thenReturnsPaginationBarNumbersToList() {
        return Stream.of(
                Arguments.of(0, 10, List.of(0, 1, 2, 3, 4)),
                Arguments.of(1, 10, List.of(0, 1, 2, 3, 4)),
                Arguments.of(2, 10, List.of(0, 1, 2, 3, 4)),
                Arguments.of(3, 10, List.of(1, 2, 3, 4, 5)),
                Arguments.of(4, 10, List.of(2, 3, 4, 5, 6)),
                Arguments.of(5, 10, List.of(3, 4, 5, 6, 7)),
                Arguments.of(6, 10, List.of(4, 5, 6, 7, 8)),
                Arguments.of(7, 10, List.of(5, 6, 7, 8, 9)),
                Arguments.of(8, 10, List.of(5, 6, 7, 8, 9))
        );
    }

    @DisplayName("Inform pagination bar length")
    @Test
    void givenNothing_whenCalling_thenReturnsCurrentBarLength() {
        assertThat(sut.getBarLength()).isEqualTo(5);
    }

}
