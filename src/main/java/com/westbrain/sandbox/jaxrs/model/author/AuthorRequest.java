package com.westbrain.sandbox.jaxrs.model.author;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorRequest {
    @NotBlank(message = "author name cannot be blank!")
    private String name;
}
