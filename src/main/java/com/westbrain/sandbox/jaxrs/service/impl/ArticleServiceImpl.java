package com.westbrain.sandbox.jaxrs.service.impl;


import com.westbrain.sandbox.jaxrs.entity.Article;
import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.repository.ArticleRepository;
import com.westbrain.sandbox.jaxrs.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleResponse> getAll() {
        List<Article> articles = articleRepository.findAll();
        log.info("Successfully get all articles");
        return articles.stream().map(ArticleResponse::responseFromArticle).collect(Collectors.toList());
    }

    @Override
    public ArticleResponse get(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new BadRequestException("entity with id: " + id + " not found"));
        log.info("Successfully get article with id: " + id);
        return ArticleResponse.responseFromArticle(article);
    }

    @Override
    public ArticleResponse create(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setDescription(articleRequest.getDescription());
        log.info("Successfully create article!");
        return ArticleResponse.responseFromArticle(articleRepository.save(article));
    }

    @Override
    public ArticleResponse update(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new BadRequestException("entity with id: " + id + " not found"));
        article.setDescription(articleRequest.getDescription());
        log.info("Successfully update article with id: " + id);
        return ArticleResponse.responseFromArticle(articleRepository.save(article));
    }

    @Override
    public void delete(Long id) {
        if (articleRepository.findById(id).isEmpty()) {
            throw new BadRequestException("entity with id: " + id + " not found!!");
        }
        log.info("Successfully delete article with id: " + id);
        articleRepository.deleteById(id);
    }
}
