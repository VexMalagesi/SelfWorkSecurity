package org.example.SpringBoot.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;


@Entity

@Table(name = "authors")
// @JsonIgnoreProperties({"id"})
public class Author {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstname", nullable = true)
    private String name;

    @Column(name = "lastname", nullable = true)
    private String surname;


    @Column(nullable = false, unique = true)
    private String email;


    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties({"author"})
    private List<Post> posts;


    public Author() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Post> getPosts() { return posts; }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
