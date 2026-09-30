package org.example.SpringBoot.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

  import java.util.List;

@Entity
@Table(name = "posts")
//@JsonIgnoreProperties({"author"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String body;

    @Column(nullable = false, length = 8)
    private String publishDate;

    @ManyToOne

    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties({"posts"})
    private Author author;


       @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<Comment> comments;

    public Post() {
    }

      public Long getId() { return id; }

        public void setId(Long id) { this.id = id; }

        public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }

      public void setBody(String body) { this.body = body; }

      public String getPublishDate() { return publishDate; }

     public void setPublishDate(String publishDate) { this.publishDate = publishDate; }

    public Author getAuthor() { return author; }

     public void setAuthor(Author author) { this.author = author; }

     public List<Comment> getComments() { return comments; }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
