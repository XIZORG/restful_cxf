package com.westbrain.sandbox.jaxrs.article;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creator;
}
