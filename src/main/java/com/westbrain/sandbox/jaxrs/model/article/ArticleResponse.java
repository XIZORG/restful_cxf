package com.westbrain.sandbox.jaxrs.model.article;

import com.westbrain.sandbox.jaxrs.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    private String description;
    private String name;
    private Long id;
    private Set<Author> authors;
}
