package com.xworkz.library_mangement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "library_table")
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "price")
    private Double price;

    @Column(name = "genre")
    private String genre;

    @Column(name = "book_code")
    private String bookCode;
}
