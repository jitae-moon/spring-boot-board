package com.example.springbootboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @ManyToOne
    private Article article;

    protected ArticleComment() { }

    protected ArticleComment(String content, Article article) {
        this.content = content;
        this.article = article;
    }

    public static ArticleComment of(String content, Article article) {
        return new ArticleComment(content, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment articleComment)) return false;
        return Objects.equals(id, articleComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
