package com.westbrain.sandbox.jaxrs.repository;

import com.westbrain.sandbox.jaxrs.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(Long id);
}
