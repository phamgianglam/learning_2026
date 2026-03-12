package com.store.api.service;

import com.store.api.util.Helper;
import com.store.api.dto.LoginUserDto;
import com.store.api.model.User;
import com.store.api.repository.UserRepository;
import com.store.api.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
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
    private UserTokenRepository userTokenRepository;

    @Autowired
    private Helper helper;

    public String login(LoginUserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow(
                () -> new AuthenticationException("Invalid username or password") {;
        });

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid username or password") {
            };
        }

        return helper.createToken(user.getId());
    }

    public String logout() {
        // Implement logout logic if needed, such as invalidating tokens or clearing session data
        return "Logout successful";
    }

}
