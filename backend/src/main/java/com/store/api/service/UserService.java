package com.store.api.service;

import com.store.api.dto.CreateUserDto;
import com.store.api.model.User;
import com.store.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Creates a new user after validating uniqueness and encoding password.
     * @param dto create user payload
     * @return saved User
     * @throws IllegalArgumentException if username or email is already used
     */
    public User createUser(CreateUserDto dto) {
        Optional<User> byUsername = userRepository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()) {
            throw new IllegalArgumentException("Username already in use");
        }

        Optional<User> byEmail = userRepository.findByEmail(dto.getEmail());
        if (byEmail.isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole() == null ? "USER" : dto.getRole())
                .build();

        return userRepository.save(user);
    }
}

