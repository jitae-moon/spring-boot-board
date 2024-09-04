package com.example.springbootboard.repository.article.querydsl;

import com.example.springbootboard.domain.Article;
import com.example.springbootboard.domain.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CustomArticleRepositoryImpl extends QuerydslRepositorySupport implements CustomArticleRepository {

    public CustomArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }

}
