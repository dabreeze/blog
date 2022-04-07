package com.blog.blog.data.dto.request;

import lombok.Data;

@Data
public class AppUserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String UserName;
    private String imgUrl;

}
