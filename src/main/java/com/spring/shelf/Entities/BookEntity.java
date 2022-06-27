package com.spring.shelf.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false, unique = true)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "owner", nullable = false)
    private String UserOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "shelfId",nullable = false)
    private ShelfEntity shelf;





}
