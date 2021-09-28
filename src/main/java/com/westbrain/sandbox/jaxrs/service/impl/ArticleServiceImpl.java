package com.westbrain.sandbox.jaxrs.service.impl;


import com.westbrain.sandbox.jaxrs.entity.Article;
import com.westbrain.sandbox.jaxrs.entity.Author;
import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponseMapper;
import com.westbrain.sandbox.jaxrs.model.article.ArticleUpdateRequest;
import com.westbrain.sandbox.jaxrs.repository.ArticleRepository;
import com.westbrain.sandbox.jaxrs.repository.AuthorRepository;
import com.westbrain.sandbox.jaxrs.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;
    private final ArticleResponseMapper articleMapper = Mappers.getMapper(ArticleResponseMapper.class);

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, AuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public List<ArticleResponse> getAll() {
        List<Article> articles = articleRepository.findAll();
        log.info("Getting all articles");
        return articles.stream().map(articleMapper::articleToArticleResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ArticleResponse get(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new BadRequestException("article with id: " + id + " not found"));
        log.info("Getting article with id: " + id);
        return articleMapper.articleToArticleResponse(article);
    }

    @Override
    @Transactional
    public ArticleResponse create(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setDescription(articleRequest.getDescription());
        article.setName(articleRequest.getName());
        if (articleRequest.getAuthorsId() != null) {
            Set<Author> authors = articleRequest.getAuthorsId().stream()
                    .map(data -> authorRepository.findById(data).orElseThrow(() ->
                            new BadRequestException("author with id: " + data + " not found")))
                    .collect(Collectors.toSet());
            authors.forEach(author -> author.addArticle(article));
        }

        log.info("Creating article!");
        return articleMapper.articleToArticleResponse(articleRepository.save(article));
    }

    @Override
    @Transactional
    public ArticleResponse update(ArticleUpdateRequest articleRequest, Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new BadRequestException("article with id: " + id + " not found"));
        article.setDescription(articleRequest.getDescription());
        article.setName(articleRequest.getName());
        log.info("Updating article with id: " + id);
        return articleMapper.articleToArticleResponse(articleRepository.save(article));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting article with id: " + id);
        if (articleRepository.findById(id).isEmpty()) {
            throw new BadRequestException("article with id: " + id + " not found!!");
        }

        articleRepository.deleteById(id);
        log.info("Successfully delete article with id: " + id);
    }
}
