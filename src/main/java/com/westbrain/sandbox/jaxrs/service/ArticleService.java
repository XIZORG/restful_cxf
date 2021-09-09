package com.westbrain.sandbox.jaxrs.service;

import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;

import java.util.List;

public interface ArticleService {
    List<ArticleResponse> getAll();
    ArticleResponse create(ArticleRequest articleRequest);
    ArticleResponse update(Long id, ArticleRequest articleRequest);
    void delete(Long id);
}
