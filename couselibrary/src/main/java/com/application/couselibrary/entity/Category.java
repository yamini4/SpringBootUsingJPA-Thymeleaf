package com.application.couselibrary.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length=50, nullable = false,unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories", cascade=CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }
}
