package com.westbrain.sandbox.jaxrs.model.author;

import com.westbrain.sandbox.jaxrs.entity.Author;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorResponseMapper {
    AuthorResponse authorToAuthorResponse(Author source);
    Author authorResponseToAuthor(AuthorResponse destination);

    AuthorGetResponse authorToGetResponse(Author source);
    Author getResponseToAuthor(AuthorGetResponse destination);

    Author authorRequestToAuthor(AuthorRequest authorRequest);
}
