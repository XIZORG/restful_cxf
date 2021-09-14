package com.westbrain.sandbox.jaxrs.model.article;


import com.westbrain.sandbox.jaxrs.entity.Article;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleResponseMapper {
    ArticleResponse sourceToDestination(Article source);
    Article destinationToSource(ArticleResponse destination);
}
