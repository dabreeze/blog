package com.blog.blog.data.models;

import jdk.jfr.Category;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false, length = 100)
    private String postTitle;

    @Column(nullable = false, length = 10000)
    private String postBody;

    @CreationTimestamp
    private LocalDateTime datePosted;

    @ElementCollection
    private List<PostCategory> postCategory;

    @ElementCollection
    private List<String> imgUrl;

    @ElementCollection
    private List<Likes> likes;

    @OneToMany
    private List<Comments> comments;

    @Column(nullable = false, length = 50)
    private String authorsName;

    public void addComments(){
        if(comments == null){
            comments = new ArrayList<>();
        }
    }

}
