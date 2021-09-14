package com.westbrain.sandbox.jaxrs.model.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleSubscribeRequest {
    @NotBlank(message = "id cannot be blank!")
    private Long id;
}
