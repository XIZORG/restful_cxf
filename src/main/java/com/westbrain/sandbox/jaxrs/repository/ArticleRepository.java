package com.westbrain.sandbox.jaxrs.repository;

import com.westbrain.sandbox.jaxrs.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
