package com.westbrain.sandbox.jaxrs.model.author;

import com.westbrain.sandbox.jaxrs.entity.Author;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorResponseMapper {
    AuthorResponse sourceToDestination(Author source);
    Author destinationToSource(AuthorResponse destination);
}
