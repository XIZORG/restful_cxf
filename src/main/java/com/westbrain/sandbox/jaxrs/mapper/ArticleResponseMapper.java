package com.westbrain.sandbox.jaxrs.mapper;


import com.westbrain.sandbox.jaxrs.entity.Article;
import com.westbrain.sandbox.jaxrs.model.article.ArticleResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleResponseMapper {
    ArticleResponse articleToArticleResponse(Article source);
    Article articleResponseToArtilce(ArticleResponse destination);
}
