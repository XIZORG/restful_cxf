package com.westbrain.sandbox.jaxrs.controller;


import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api("Article controller")
@Service
@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GET
    @Path("/")
    @ApiOperation("Get all articles")
    public List<ArticleResponse> getAllArticles() {
        return articleService.getAll();
    }

    @POST
    @Path("/")
    @ApiOperation("Create new article")
    public ArticleResponse createArticle(ArticleRequest articleRequest){
        return articleService.create(articleRequest);
    }

    @GET
    @Path("/{articleId}")
    @ApiOperation("Get article")
    public ArticleResponse getArticle(@PathParam("articleId") Long id) {
        return articleService.get(id);
    }

    @PUT
    @Path("/{articleId}")
    @ApiOperation(value = "Update article")
    public ArticleResponse updateArticle(ArticleRequest articleRequest, @PathParam("articleId") Long id){
        return articleService.update(id, articleRequest);
    }

    @DELETE
    @Path("/{articleId}")
    @ApiOperation(value = "Delete article")
    public void deleteArticle(@PathParam("articleId") Long id){
        articleService.delete(id);
    }

}
