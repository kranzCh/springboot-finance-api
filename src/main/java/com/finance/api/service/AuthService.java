package com.finance.api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finance.api.dto.request.LoginRequest;
import com.finance.api.dto.request.RegisterRequest;
import com.finance.api.dto.response.AuthResponse;
import com.finance.api.dto.response.RegisterResponse;
import com.finance.api.model.User;
import com.finance.api.repository.UserRepository;
import com.finance.api.security.JwtService;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService, PasswordEncoder passwordEncoder, UserRepository userRepository,
            AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(RegisterRequest request) {
        // Check duplicate username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        var encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPasswordHash(encodedPassword);
        // Save user to database
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new RegisterResponse(request.getUsername(), token);
    }

    public AuthResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword());
        this.authenticationManager.authenticate(authToken);
        var user = userRepository.findByUsername(request.getUsername());
        String token = jwtService.generateToken(user.get());
        return new AuthResponse(request.getUsername(), token);
    }
}
