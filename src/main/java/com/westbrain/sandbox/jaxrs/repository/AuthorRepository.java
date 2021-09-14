package com.westbrain.sandbox.jaxrs.repository;

import com.westbrain.sandbox.jaxrs.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
