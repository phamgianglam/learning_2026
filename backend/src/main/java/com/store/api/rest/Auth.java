package com.store.api.rest;

import com.store.api.dto.LoginUserDto;
import com.store.api.service.AuthService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class Auth {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(LoginUserDto userDto) {
        return authService.login(userDto);
    }

    @GetMapping("/Logout")
    public String logout(@PathParam("userName") String userName) {
        return authService.logout();
    }
}
