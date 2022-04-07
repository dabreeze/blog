package com.blog.blog.service;

import com.blog.blog.data.dto.request.AppUserRequest;
import com.blog.blog.data.dto.response.AppUserResponse;
import com.blog.blog.data.models.AppUser;
import com.blog.blog.web.exceptions.BussinessLogicException;
import com.blog.blog.web.exceptions.UserNotFoundException;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface AppUserService {
    AppUserResponse createANewUser(AppUserRequest appUserRequest) throws UserNotFoundException;
    List<AppUser> getAllUsers();
    AppUser findUserById(Long id) throws BussinessLogicException;
    AppUser updateAppUser(Long appUserId, JsonPatch patch) throws BussinessLogicException;
    void deleteAppUser(AppUserRequest admin);

}
