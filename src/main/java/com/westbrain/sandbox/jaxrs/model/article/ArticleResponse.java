package com.westbrain.sandbox.jaxrs.model.article;

import com.westbrain.sandbox.jaxrs.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse implements Serializable {
    private String description;
    private String name;
    private Long id;
    private Set<Author> authors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleResponse that = (ArticleResponse) o;
        return Objects.equals(description, that.description) && Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name, id);
    }

}
