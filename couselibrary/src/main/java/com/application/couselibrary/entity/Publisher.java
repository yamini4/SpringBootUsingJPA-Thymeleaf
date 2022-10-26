package com.application.couselibrary.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", length = 50, nullable = false,unique = true)
    private String name;

    @ManyToMany(mappedBy = "publishers",cascade=CascadeType.ALL)
    private Set<Book> books= new HashSet<>();

    public Publisher(String name) {
        this.name = name;
    }
}
