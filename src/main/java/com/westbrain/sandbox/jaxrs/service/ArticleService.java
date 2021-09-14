package com.westbrain.sandbox.jaxrs.service;

import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api("Article controller")
@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
@Validated
public interface ArticleService {
    @GET
    @Path("/")
    @ApiOperation("Get all articles")
    List<ArticleResponse> getAll();

    @GET
    @Path("/{articleId}")
    @ApiOperation("Get article")
    ArticleResponse get(@PathParam("articleId") Long id);

    @POST
    @Path("/")
    @ApiOperation("Create new article")
    ArticleResponse create(@Valid ArticleRequest articleRequest);

    @PUT
    @Path("/{articleId}")
    @ApiOperation(value = "Update article")
    ArticleResponse update(ArticleRequest articleRequest, @PathParam("articleId") Long id);

    @DELETE
    @Path("/{articleId}")
    @ApiOperation(value = "Delete article")
    void delete(@PathParam("articleId") Long id);
}
