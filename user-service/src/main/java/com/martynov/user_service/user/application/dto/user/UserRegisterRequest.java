package com.martynov.user_service.user.application.dto.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRegisterRequest {
    public String username;
    public String password;
}
