package com.westbrain.sandbox.jaxrs.model.article;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class ArticleRequest {
    @NotBlank(message = "article!")
    public String description;
}
