package com.westbrain.sandbox.jaxrs.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleController {
    @Autowired
    private ArticleRepository repository;

    @GET
    public Iterable<Article> getAllGroups() {
        return repository.findAll();
    }
}
