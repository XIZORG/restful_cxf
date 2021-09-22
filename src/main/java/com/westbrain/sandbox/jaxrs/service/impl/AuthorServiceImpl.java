package com.westbrain.sandbox.jaxrs.service.impl;

import com.westbrain.sandbox.jaxrs.entity.Article;
import com.westbrain.sandbox.jaxrs.entity.Author;
import com.westbrain.sandbox.jaxrs.model.article.ArticleSubscribeRequest;
import com.westbrain.sandbox.jaxrs.model.author.AuthorGetResponse;
import com.westbrain.sandbox.jaxrs.model.author.AuthorRequest;
import com.westbrain.sandbox.jaxrs.model.author.AuthorResponse;
import com.westbrain.sandbox.jaxrs.model.author.AuthorResponseMapper;
import com.westbrain.sandbox.jaxrs.repository.ArticleRepository;
import com.westbrain.sandbox.jaxrs.repository.AuthorRepository;
import com.westbrain.sandbox.jaxrs.service.AuthorService;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;
    private final AuthorResponseMapper authorMapper = Mappers.getMapper(AuthorResponseMapper.class);

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ArticleRepository articleRepository) {
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional
    public List<AuthorResponse> getAll() {
        List<Author> authors = authorRepository.findAll();
        log.info("authors all authors");
        return authors.stream().map(authorMapper::authorToAuthorResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthorGetResponse get(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new BadRequestException("author with id: " + id + " not found"));
        log.info("Getting author with id: " + id);
        return authorMapper.authorToGetResponse(author);
    }

    @Override
    public AuthorResponse create(AuthorRequest authorRequest) {
        Author author = authorMapper.authorRequestToAuthor(authorRequest);
        log.info("Creating author!");
        return authorMapper.authorToAuthorResponse(authorRepository.save(author));
    }

    @Override
    public AuthorResponse update(AuthorRequest authorRequest, Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new BadRequestException("author with id: " + id + " not found"));
        author.setName(authorRequest.getName());
        log.info("Updating author with id: " + id);
        return authorMapper.authorToAuthorResponse(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting author with id: " + id);
        if (authorRepository.findById(id).isEmpty()) {
            throw new BadRequestException("author with id: " + id + " not found!!");
        }
        authorRepository.deleteById(id);
        log.info("Successfully delete author with id: " + id);
    }

    @Override
    @Transactional
    public void addAuthorToArticle(ArticleSubscribeRequest request, Long id) {
        Article article = articleRepository.findById(request.getId()).orElseThrow(() ->
                new BadRequestException("article with id: " + id + " not found"));
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new BadRequestException("author with id: " + id + " not found"));
        author.addArticle(article);
        authorRepository.save(author);
        articleRepository.save(article);
        log.info("Adding article with id: " + request.getId() + "to author with id: " + id);
    }
}
