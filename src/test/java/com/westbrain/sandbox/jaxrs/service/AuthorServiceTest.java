package com.westbrain.sandbox.jaxrs.service;

import com.westbrain.sandbox.jaxrs.entity.Article;
import com.westbrain.sandbox.jaxrs.entity.Author;
import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.model.author.AuthorGetResponse;
import com.westbrain.sandbox.jaxrs.model.author.AuthorRequest;
import com.westbrain.sandbox.jaxrs.model.author.AuthorResponse;
import com.westbrain.sandbox.jaxrs.repository.ArticleRepository;
import com.westbrain.sandbox.jaxrs.repository.AuthorRepository;
import com.westbrain.sandbox.jaxrs.service.impl.ArticleServiceImpl;
import com.westbrain.sandbox.jaxrs.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorServiceTest {
    private ArticleRepository articleRepository;
    private AuthorService authorService;
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        articleRepository = mock(ArticleRepository.class);
        authorRepository = mock(AuthorRepository.class);
        authorService = new AuthorServiceImpl(authorRepository, articleRepository);
    }

    @Test
    void testCreateArticle(){
        String name = "some text to name";

        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName(name);

        Author author = new Author();
        author.setName(authorRequest.getName());

        when(authorRepository.save(author)).thenReturn(author);

        AuthorResponse authorResponse = authorService.create(authorRequest);
        assertNotNull(authorResponse);
        assertEquals(authorResponse.getName(), authorRequest.getName());
    }

    @Test
    void testGetArticle(){
        Long id = 1L;
        String name = "some text to name";

        Author author = new Author();
        author.setName(name);
        author.setId(id);

        when(authorRepository.findById(id)).thenReturn(java.util.Optional.of(author));
        AuthorGetResponse authorResponse = authorService.get(id);
        assertNotNull(authorResponse);

        assertEquals(authorResponse.getName(), name);
        assertEquals(authorResponse.getId(), id);
    }

    @Test
    void testGetAllArticles(){
        Long id = 1L;
        String name = "some text to name";

        Author author = new Author();
        author.setName(name);
        author.setId(id);

        List<Author> authorList = List.of(author);

        when(authorRepository.findAll()).thenReturn(authorList);
        List<AuthorResponse> authorResponses = authorService.getAll();
        assertNotNull(authorResponses);
        assertNotNull(authorResponses.get(0));

        AuthorResponse authorResponse = authorResponses.get(0);
        assertEquals(authorResponse.getName(), name);
        assertEquals(authorResponse.getId(), id);
    }

    @Test
    void testUpdateArticle() {
        Long id = 1L;
        String name = "some text to name";
        String newName = "new text";

        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setName(newName);

        Author author = new Author();
        author.setName(name);
        author.setId(id);

        when(authorRepository.findById(id)).thenReturn(java.util.Optional.of(author));
        when(authorRepository.save(author)).thenReturn(author);

        AuthorResponse authorResponse = authorService.update(authorRequest, id);
        assertNotNull(authorResponse);
        assertEquals(authorResponse.getName(), newName);
        assertEquals(authorResponse.getId(), id);
    }

    @Test
    void testDeleteArticle() {
        Long id = 1L;
        String name = "some text to name";
        Author author = new Author();
        author.setName(name);
        author.setId(id);

        when(authorRepository.findById(id)).thenReturn(java.util.Optional.of(author));

        authorService.delete(id);
        verify(authorRepository).deleteById(id);
    }
}
