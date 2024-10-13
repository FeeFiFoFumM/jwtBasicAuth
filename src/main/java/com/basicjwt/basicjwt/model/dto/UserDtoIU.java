package com.basicjwt.basicjwt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoIU {

    private String username;

    private String password;

    private String email;
    
}
