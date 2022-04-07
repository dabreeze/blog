package com.blog.blog.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String userName;

    @Transient
    private String fullName;

    @Column(nullable = false, length = 50)
    private String phone;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Posts> post;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comments> comments;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    public void addPost(Posts post){
        if(post == null){
            post = new Posts();
        }
    }
    public  void addComment(Posts posts){
        if(posts != null){
            comments = new ArrayList<>();
        }
    }
    public String getFullName(){
        return firstName + " "+ lastName;
    }
}
