package com.westbrain.sandbox.jaxrs.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
