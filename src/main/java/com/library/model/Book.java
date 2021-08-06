package com.library.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "title is mandatory")
    private String title;
    @NotBlank(message = "author is mandatory")
    private String author;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "ISBN is mandatory")
    private String ISBN;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
