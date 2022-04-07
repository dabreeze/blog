package com.blog.blog.data.repository;

import com.blog.blog.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    //AppUser findByUserName(String username);
    Optional<AppUser> findByEmail(String email);

}
