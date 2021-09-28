
package com.westbrain.sandbox.jaxrs.model.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleUpdateRequest {
    @NotBlank(message = "article description cannot be blank!")
    private String description;
    @NotBlank(message = "article name cannot be blank!")
    private String name;
}
