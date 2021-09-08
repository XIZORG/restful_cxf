package com.westbrain.sandbox.jaxrs.article;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleController {
    @Autowired
    private ArticleRepository repository;

    @GET
    public List<Article> getAllGroups() {
        return repository.findAll();
    }
}
