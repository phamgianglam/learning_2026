package com.store.api.service;

import com.store.api.dto.LoginUserDto;
import com.store.api.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserToknRepository userToknRepository;

    public String login(LoginUserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

        }
        return "token";
    }
}
