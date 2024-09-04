package com.example.springbootboard.service.article;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
        int startNumber = Math.min(Math.max(currentPageNumber - (BAR_LENGTH / 2), 0), totalPages - BAR_LENGTH);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    public int getBarLength() {
        return BAR_LENGTH;
    }

}
