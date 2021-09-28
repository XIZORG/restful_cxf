package com.westbrain.sandbox.jaxrs.service;

import com.westbrain.sandbox.jaxrs.model.article.ArticleRequest;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import com.westbrain.sandbox.jaxrs.model.article.ArticleSubscribeRequest;
import com.westbrain.sandbox.jaxrs.model.author.AuthorGetResponse;
import com.westbrain.sandbox.jaxrs.model.author.AuthorRequest;
import com.westbrain.sandbox.jaxrs.model.author.AuthorResponse;
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

@Api("Author controller")
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Validated
public interface AuthorService {
    @GET
    @Path("/")
    @ApiOperation("Get all authors")
    List<AuthorResponse> getAll();

    @GET
    @Path("/{authorId}")
    @ApiOperation("Get author")
    AuthorGetResponse get(@PathParam("authorId") Long id);

    @POST
    @Path("/")
    @ApiOperation("Create new author")
    AuthorResponse create(@Valid AuthorRequest authorRequest);

    @PUT
    @Path("/{authorId}")
    @ApiOperation(value = "Update author")
    AuthorResponse update(@Valid AuthorRequest authorRequest, @PathParam("authorId") Long id);

    @DELETE
    @Path("/{authorId}")
    @ApiOperation(value = "Delete author")
    void delete(@PathParam("authorId") Long id);

    @POST
    @Path("/{authorId}/article")
    @ApiOperation("Add article to author")
    void addAuthorToArticle(@Valid ArticleSubscribeRequest request, @PathParam("authorId") Long id);
}
