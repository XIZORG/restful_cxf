package com.westbrain.sandbox.jaxrs.service.impl;

import com.westbrain.sandbox.jaxrs.model.article.Article;
import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.repository.ArticleRepository;
import com.westbrain.sandbox.jaxrs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleResponse> getAll() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(ArticleResponse::responseFromArticle).collect(Collectors.toList());
    }

    @Override
    public ArticleResponse create(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setDescription(articleRequest.getDescription());
        return ArticleResponse.responseFromArticle(articleRepository.save(article));
    }

    @Override
    public ArticleResponse update(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id);
        article.setDescription(articleRequest.getDescription());
        return ArticleResponse.responseFromArticle(articleRepository.save(article));
    }

    @Override
    public void delete(Long id) {
        articleRepository.delete(id);
    }
}
