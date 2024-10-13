package com.basicjwt.basicjwt.request;


import com.basicjwt.basicjwt.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    User user;
    RegisterRequest(User user){
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
    }
    private String username;

    private String password;

    private String email;
}
