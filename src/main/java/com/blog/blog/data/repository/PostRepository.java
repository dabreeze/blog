package com.blog.blog.data.repository;

import com.blog.blog.data.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<Posts, Long> {
    public Posts findPostByPostTitle(String name);
    public Posts findPostByAuthorsName(String name);
}
