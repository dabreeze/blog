package com.blog.blog.service;

import com.blog.blog.data.models.AppUser;
import com.blog.blog.data.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql( scripts = {"/db/insert.sql"})
//@Transactional
class AppUserServiceImplTest {

    @Autowired
    AppUserService appUserService;

    @Autowired
    AppUserRepository appUserRepository;

    AppUser aUser ;

    @BeforeEach
    void setUp() {
        aUser = new AppUser();
    }
    // transactional rolls back any unsaved data if its not successful
    //@Transactional
    @Test
    void createANewUser() {
        aUser.setFirstName("harrison");
        aUser.setLastName("Ogbonnaya");
        aUser.setUserName("dabreeze");
        aUser.setDateCreated(LocalDateTime.now());
        aUser.setEmail("dabreeze@gmail.com");
        aUser.setPhone("08076565434");
        // save to the repository
        appUserRepository.save(aUser);
        log.info("this is a user -->{}",aUser);
        AppUser appUser = appUserRepository.findByEmail("dabreeze@gmail.com").get();

        assertThat(appUserRepository.findById(1L)).isNotNull();
        assertThat(appUser.getEmail()).isEqualTo("dabreeze@gmail.com");


    }

    @Test
    void getAllUsers() {

        assertThat(appUserRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void findUserById() {
        AppUser appUser = appUserRepository.findById(2L).orElse(null);
        assertThat(appUser.getId()).isEqualTo(2);
        assertThat(appUser.getFirstName()).isEqualTo("kelvin");

    }

    @Test
    void updateAppUser() {
        AppUser appUser = new AppUser();
        appUser = appUserRepository.findById(1L).orElse(null);
        assertThat(appUser).isNotNull();
        assertThat(appUser.getEmail()).isEqualTo("jerrywise@gmail.com");
        log.info("old email change --> :: {}", appUser);
        appUser.setEmail("wise@gmail.com");
        appUserRepository.save(appUser);
        assertThat(appUser.getEmail()).isEqualTo("wise@gmail.com");
        log.info("new email change --> :: {}", appUser);
    }

    @Test
    void deleteAppUser() {
       AppUser appUser = new AppUser();
         appUser = appUserRepository.findById(2L).orElse(null);
        assertThat(appUser).isEqualTo(2L);
        appUserRepository.delete(appUser);
        appUserRepository.findById(2L).isEmpty();
        assertThat(appUser.getId()).isNull();
//        assertThat(appUserRepository.findAll().size()).isEqualTo(2);
    }
}