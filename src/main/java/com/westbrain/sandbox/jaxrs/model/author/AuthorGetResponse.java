package com.westbrain.sandbox.jaxrs.model.author;

import com.westbrain.sandbox.jaxrs.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorGetResponse {
    private String name;
    private Long id;
    private Set<Article> articles;
}
