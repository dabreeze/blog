package com.blog.blog.data.repository;

import com.blog.blog.data.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comments, Long> {
    Comments findByCategoryName (String categoryName);
}
