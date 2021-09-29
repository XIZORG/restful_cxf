package com.westbrain.sandbox.jaxrs.mapper;

import com.westbrain.sandbox.jaxrs.entity.Author;
import com.westbrain.sandbox.jaxrs.model.author.AuthorGetResponse;
import com.westbrain.sandbox.jaxrs.model.author.AuthorRequest;
import com.westbrain.sandbox.jaxrs.model.author.AuthorResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorResponseMapper {
    AuthorResponse authorToAuthorResponse(Author source);
    Author authorResponseToAuthor(AuthorResponse destination);

    AuthorGetResponse authorToGetResponse(Author source);
    Author getResponseToAuthor(AuthorGetResponse destination);

    Author authorRequestToAuthor(AuthorRequest authorRequest);
}
