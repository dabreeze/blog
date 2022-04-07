package com.blog.blog.service;

import com.blog.blog.data.dto.request.AppUserRequest;
import com.blog.blog.data.dto.response.AppUserResponse;
import com.blog.blog.data.models.AppUser;
import com.blog.blog.data.repository.AppUserRepository;
import com.blog.blog.web.exceptions.BussinessLogicException;
import com.blog.blog.web.exceptions.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    AppUserRepository appUserRepository;

//    @Autowired
//    AppUserService appUserService;

    @Override
    public AppUserResponse createANewUser(AppUserRequest appUserRequest) throws UserNotFoundException {
        if(appUserRequest == null)throw new IllegalArgumentException("Invalid Request");

        // check if user exist
        Optional<AppUser> query = appUserRepository.findById(appUserRequest.getId());
        if(query.isPresent()){
            throw new IllegalArgumentException("This user already exist");
        }

        ModelMapper model = new ModelMapper();

        AppUser savedUser = model.map(appUserRequest, AppUser.class);

        appUserRepository.save(savedUser);

        return model.map(savedUser, AppUserResponse.class);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findUserById(Long id) throws BussinessLogicException {
        AppUserRequest appUserRequest = new AppUserRequest();
        if(id == null){throw new IllegalArgumentException("id cannot be null");}
        Optional<AppUser> query = appUserRepository.findById(appUserRequest.getId());
        if(query.isPresent()){
            return query.get();
        }else {
            throw new BussinessLogicException("this user does not exist");

        }
    }

    @Override
    public AppUser updateAppUser(Long appUserId, JsonPatch patch) throws BussinessLogicException {
        // check if user exist
        Optional<AppUser> optionalUser = appUserRepository.findById(appUserId);
        if( optionalUser.isEmpty()) throw new BussinessLogicException("No user for this Id");

        AppUser targetAppUser = optionalUser.get();

        return getUser(patch, targetAppUser);
    }

    private AppUser getUser(JsonPatch patch, AppUser targetAppUser) throws BussinessLogicException {
        try{
            targetAppUser = applyPatchToAppUser(patch, targetAppUser);
            return appUserRepository.save(targetAppUser);
        }catch (JsonPatchException | JsonProcessingException exception) {
            throw new BussinessLogicException("Update failed");
        }
    }

    private AppUser applyPatchToAppUser(JsonPatch patch, AppUser targetAppUser) throws JsonProcessingException, JsonPatchException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode patched = patch.apply(mapper.convertValue(targetAppUser, JsonNode.class));

        return mapper.treeToValue(patched, AppUser.class);
    }

    @Override
    public void deleteAppUser(AppUserRequest admin) {

    }
}
