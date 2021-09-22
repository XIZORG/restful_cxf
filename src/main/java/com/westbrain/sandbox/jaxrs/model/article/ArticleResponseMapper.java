package com.westbrain.sandbox.jaxrs.model.article;


import com.westbrain.sandbox.jaxrs.entity.Article;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleResponseMapper {
    ArticleResponse articleToArticleResponse(Article source);
    Article articleResponseToArtilce(ArticleResponse destination);
}
