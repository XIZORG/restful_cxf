package com.westbrain.sandbox.jaxrs.article;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "courses")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creator;
}
