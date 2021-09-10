package com.westbrain.sandbox.jaxrs.service;

import com.westbrain.sandbox.jaxrs.model.article.Article;
import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.repository.ArticleRepository;
import com.westbrain.sandbox.jaxrs.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ArticleServiceTest {
    private ArticleRepository articleRepository;
    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        articleRepository = mock(ArticleRepository.class);
        articleService = new ArticleServiceImpl(articleRepository);
    }

    @Test
    void testCreateArticle(){
        String descr = "some text to test";

        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setDescription(descr);

        Article article = new Article();
        article.setDescription(articleRequest.getDescription());

        when(articleRepository.save(article)).thenReturn(article);

        ArticleResponse articleResponse = articleService.create(articleRequest);
        assertNotNull(articleResponse);
        assertEquals(articleResponse.getDescription(), articleRequest.description);
    }

    @Test
    void testGetArticle(){
        Long id = 1L;
        String descr = "some text to test";

        Article article = new Article();
        article.setId(id);
        article.setDescription(descr);

        when(articleRepository.findById(id)).thenReturn(article);
        ArticleResponse articleResponse = articleService.get(id);
        assertNotNull(articleResponse);

        assertEquals(articleResponse.getDescription(), descr);
        assertEquals(articleResponse.getId(), id);
    }

    @Test
    void testGetAllArticles(){
        Long id = 1L;
        String descr = "some text to test";

        Article article = new Article();
        article.setId(id);
        article.setDescription(descr);

        List<Article> articleList = List.of(article);

        when(articleRepository.findAll()).thenReturn(articleList);
        List<ArticleResponse> articleResponses = articleService.getAll();
        assertNotNull(articleResponses);
        assertNotNull(articleResponses.get(0));

        ArticleResponse articleResponse = articleResponses.get(0);
        assertEquals(articleResponse.getDescription(), descr);
        assertEquals(articleResponse.getId(), id);
    }

    @Test
    void testUpdateArticle() {
        Long id = 1L;
        String descr = "some text to test";
        String newDescr = "new text";

        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setDescription(newDescr);

        Article article = new Article();
        article.setId(id);
        article.setDescription(descr);
        when(articleRepository.findById(id)).thenReturn(article);
        when(articleRepository.save(article)).thenReturn(article);

        ArticleResponse articleResponse = articleService.update(id, articleRequest);
        assertNotNull(articleResponse);
        assertEquals(articleResponse.getDescription(), newDescr);
        assertEquals(articleResponse.getId(), id);
    }

    @Test
    void testDeleteArticle() {
        Long articleId = 1L;

        articleService.delete(articleId);
        verify(articleRepository).delete(articleId);
    }
}
