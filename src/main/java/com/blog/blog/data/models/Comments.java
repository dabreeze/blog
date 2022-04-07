package com.blog.blog.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @Column(nullable = false, length = 1000)
    private String commentBody;

    @Transient
    private String commentAuthor;

    @ManyToOne
    private Posts post;

    @CreationTimestamp
    private LocalDateTime timeOfCommentCreated;


    @ManyToOne
    private AppUser appUser;

    @ElementCollection
    private List<Likes> likesList;

    public void addLikes(){
        if(commentBody != null){
            likesList = new ArrayList<>();
        }
    }
}
