package com.spring.shelf.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shelves")
@NoArgsConstructor
public class ShelfEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false)
    private long id;

    @Column(name = "shelfName", nullable = false)
    private String shelfName;

    @Column(name = "owner", nullable = false)
    private String user;

    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL)
    @Column(name = "books",nullable = false)
    private Set<BookEntity> books;

    public ShelfEntity(String username, String name){
        user=username;
        shelfName=name;
    }
}
