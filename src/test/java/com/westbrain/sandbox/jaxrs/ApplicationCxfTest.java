package com.westbrain.sandbox.jaxrs;

import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.model.author.AuthorRequest;
import com.westbrain.sandbox.jaxrs.model.author.AuthorResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith( SpringRunner.class )
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"h2db", "debug"})
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ApplicationCxfTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate rest;

    public ApplicationCxfTest() {
    }

    @Test
    public void step1testContextLoads() {
        assertNotNull(rest);
        assertNotEquals(0, port);
    }

    @Test
    public void step2testCreateArticle() {
        String url = "http://localhost:" + port + "/rest/articles";
        ArticleRequest requestBody = new ArticleRequest();
        requestBody.setDescription("some first test description");
        requestBody.setName("name");
        ResponseEntity<ArticleResponse> responseEntity = rest.postForEntity(url, requestBody, ArticleResponse.class);
        ArticleResponse articleResponse = responseEntity.getBody();
        assertNotNull(articleResponse);
    }

    @Test
    public void step3testGetArticle() {
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:" + port + "/rest/articles/1";
        ResponseEntity<ArticleResponse> articleInfo = rest.exchange(url,
                HttpMethod.GET, new HttpEntity<>(headers), ArticleResponse.class);
        ArticleResponse articleResponse = articleInfo.getBody();
        assertNotNull(articleResponse);
        assertEquals(articleInfo.getStatusCode(), HttpStatus.OK);
        Long id = 1L;
        assertEquals(articleResponse.getId(), id);
        assertEquals(articleResponse.getDescription(), "some first test description");
    }

    @Test
    public void step4testUpdateArticle() {
        HttpHeaders headers = new HttpHeaders();

        String url = "http://localhost:" + port + "/rest/articles/1";
        String newDescription = "changed descr";
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setDescription(newDescription);
        articleRequest.setName("name");
        HttpEntity<ArticleRequest> entityReq = new HttpEntity<ArticleRequest>(articleRequest, headers);

        ResponseEntity<ArticleResponse> articleInfo = rest.exchange(url,
                HttpMethod.PUT, entityReq, ArticleResponse.class);
        ArticleResponse articleResponse = articleInfo.getBody();
        assertNotNull(articleResponse);
        assertEquals(articleInfo.getStatusCode(), HttpStatus.OK);
        Long id = 1L;
        assertEquals(articleResponse.getId(), id);
        assertEquals(articleResponse.getDescription(), newDescription);
    }

    @Test
    public void step5testGetAllArticles() {
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:" + port + "/rest/articles";


        ResponseEntity<List> articleInfo = rest.exchange(url,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        List<ArticleResponse> articleResponses = articleInfo.getBody();
        Map article = (Map) articleResponses.get(0);

        assertNotNull(article);
        assertEquals(articleInfo.getStatusCode(), HttpStatus.OK);
        Integer id = 1;
        assertEquals(article.get("id"), id);
        assertEquals(article.get("description"), "changed descr");
    }

    @Test
    public void step6testDeleteArticle() {
        HttpHeaders headers = new HttpHeaders();

        String url = "http://localhost:" + port + "/rest/articles/1";
        ResponseEntity<ArticleResponse> articleInfo = rest.exchange(url,
                HttpMethod.DELETE, new HttpEntity<>(headers), ArticleResponse.class);
        assertEquals(articleInfo.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void step7testCreateAuthor() {
        String url = "http://localhost:" + port + "/rest/authors";
        AuthorRequest requestBody = new AuthorRequest();
        requestBody.setName("some first test name");
        ResponseEntity<AuthorResponse> responseEntity = rest.postForEntity(url, requestBody, AuthorResponse.class);
        AuthorResponse articleResponse = responseEntity.getBody();
        assertNotNull(articleResponse);
        assertEquals(articleResponse.getName(), "some first test name");
    }

    @Test
    public void step8testGetAuthor() {
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:" + port + "/rest/authors/1";
        ResponseEntity<AuthorResponse> responseEntity = rest.exchange(url,
                HttpMethod.GET, new HttpEntity<>(headers), AuthorResponse.class);
        AuthorResponse authorResponse = responseEntity.getBody();
        assertNotNull(authorResponse);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long id = 1L;
        assertEquals(authorResponse.getId(), id);
        assertEquals(authorResponse.getName(), "some first test name");
    }

    @Test
    public void step9testUpdateAuthor() {
        HttpHeaders headers = new HttpHeaders();

        String url = "http://localhost:" + port + "/rest/authors/1";
        String newName = "changed name";
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName(newName);
        HttpEntity<AuthorRequest> entityReq = new HttpEntity<AuthorRequest>(authorRequest, headers);

        ResponseEntity<AuthorResponse> responseEntity = rest.exchange(url,
                HttpMethod.PUT, entityReq, AuthorResponse.class);
        AuthorResponse authorResponse = responseEntity.getBody();
        assertNotNull(authorResponse);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Long id = 1L;
        assertEquals(authorResponse.getId(), id);
        assertEquals(authorResponse.getName(), newName);
    }

    @Test
    public void stepAtestGetAllAuthors() {
        HttpHeaders headers = new HttpHeaders();
        String url = "http://localhost:" + port + "/rest/authors";


        ResponseEntity<List> articleInfo = rest.exchange(url,
                HttpMethod.GET, new HttpEntity<>(headers), List.class);

        List<AuthorResponse> responseEntity = articleInfo.getBody();
        Map article = (Map) responseEntity.get(0);

        assertNotNull(article);
        assertEquals(articleInfo.getStatusCode(), HttpStatus.OK);
        Integer id = 1;
        assertEquals(article.get("id"), id);
        assertEquals(article.get("name"), "changed name");
    }

    @Test
    public void stepBtestDeleteArticle() {
        HttpHeaders headers = new HttpHeaders();

        String url = "http://localhost:" + port + "/rest/authors/1";
        ResponseEntity<AuthorResponse> responseEntity = rest.exchange(url,
                HttpMethod.DELETE, new HttpEntity<>(headers), AuthorResponse.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
