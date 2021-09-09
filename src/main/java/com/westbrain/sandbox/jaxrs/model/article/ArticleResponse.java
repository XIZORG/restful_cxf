package com.westbrain.sandbox.jaxrs.model.article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleResponse {
    private String description;
    private Long id;

    public static ArticleResponse responseFromArticle(Article article) {
        return new ArticleResponse(article.getDescription(), article.getId());
    }
}
