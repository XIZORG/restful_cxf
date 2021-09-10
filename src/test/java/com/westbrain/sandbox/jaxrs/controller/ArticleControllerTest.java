package com.westbrain.sandbox.jaxrs.controller;


import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class ArticleControllerTest {
    private ArticleService articleService;
    private ArticleController articleController;

    @BeforeEach
    void setUp() {
        articleService = mock(ArticleService.class);
        articleController = new ArticleController(articleService);
    }

    @Test
    void testGetArticleById() {
        String descr = "some text to test";
        Long articleId = 1L;
        ArticleResponse response = new ArticleResponse(descr, articleId);

        when(articleService.get(articleId)).thenReturn(response);

        ArticleResponse articleResponse = articleController.getArticle(articleId);
        assertNotNull(articleResponse);
        assertEquals(articleResponse.getId(), articleId);
        assertEquals(articleResponse.getDescription(), descr);
    }

    @Test
    void testGetAllArticles() {
        String descr = "some text to test";
        Long articleId = 1L;
        ArticleResponse response = new ArticleResponse(descr, articleId);
        List<ArticleResponse> responses = List.of(response);

        when(articleService.getAll()).thenReturn(responses);

        List<ArticleResponse> articleResponses = articleController.getAllArticles();
        assertNotNull(articleResponses);
        assertNotNull(articleResponses.get(0));
        ArticleResponse articleResponse = articleResponses.get(0);

        assertEquals(articleResponse.getId(), articleId);
        assertEquals(articleResponse.getDescription(), descr);
    }

    @Test
    void testUpdateArticle() {
        String descr = "some text to test";
        String newDescr = "text2";
        Long articleId = 1L;

        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setDescription(descr);
        ArticleResponse response = new ArticleResponse(newDescr, articleId);

        when(articleService.update(articleId, articleRequest)).thenReturn(response);

        ArticleResponse articleResponse = articleController.updateArticle(articleRequest, articleId);
        assertNotNull(articleResponse);
        assertEquals(articleResponse.getId(), articleId);
        assertEquals(articleResponse.getDescription(), newDescr);
    }

    @Test
    void testCreateArticle() {
        String descr = "some text to test";
        Long articleId = 1L;

        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setDescription(descr);
        ArticleResponse response = new ArticleResponse(descr, articleId);

        when(articleService.create(articleRequest)).thenReturn(response);


        ArticleResponse articleResponse = articleController.createArticle(articleRequest);
        assertNotNull(articleResponse);
        assertEquals(articleResponse.getId(), articleId);
        assertEquals(articleResponse.getDescription(), descr);
    }

    @Test
    void testDeleteArticle() {
        Long articleId = 1L;

        articleController.deleteArticle(articleId);
        verify(articleService).delete(articleId);
    }
}
