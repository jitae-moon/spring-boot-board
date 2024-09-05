package com.example.springbootboard.service.article;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNumber = startNumber + BAR_LENGTH - 1;

        if (endNumber > totalPages - 1) {
            endNumber = totalPages - 1;
            startNumber = Math.max(endNumber - BAR_LENGTH + 1, 0);
        }

        return IntStream.range(startNumber, endNumber + 1).boxed().toList();
    }

    public int getBarLength() {
        return BAR_LENGTH;
    }

}
