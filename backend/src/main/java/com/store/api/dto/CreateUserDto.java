package com.store.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
    private String role;
}

