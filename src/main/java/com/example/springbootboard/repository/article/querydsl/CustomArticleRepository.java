package com.example.springbootboard.repository.article.querydsl;

import java.util.List;

public interface CustomArticleRepository {

    List<String> findAllDistinctHashtags(); // Query method only returns entity. So made new repository and method.

}
