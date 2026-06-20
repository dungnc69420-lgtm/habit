package com.habit.service.impl;

import com.habit.dto.auth.AuthResponse;
import com.habit.dto.auth.LoginRequest;
import com.habit.dto.auth.RegisterRequest;
import com.habit.model.User;
import com.habit.repository.UserRepository;
import com.habit.security.JwtTokenProvider;
import com.habit.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        User user = userRepository.save(User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .provider(User.AuthProvider.LOCAL)
                .emailVerified(false)
                .build());
        
        String token = tokenProvider.generateToken(user.getEmail());
        return toResponse(user, token);
    }
    
    public AuthResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        
        // Spring Security auth went through — load the user
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalStateException("User not found after authentication"));
        
        // Prevent Google-only users from using password login
        if (user.getProvider() != User.AuthProvider.LOCAL) {
            throw new IllegalArgumentException(
                    "This account uses " + user.getProvider() + " login. Please sign in with Google.");
        }
        
        String token = tokenProvider.generateToken(user.getEmail());
        return toResponse(user, token);
    }
    
    public AuthResponse me(UserPrincipal principal) {
        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        return toResponse(user, null); // no new token needed
    }
    
    private AuthResponse toResponse(User user, String token) {
        return AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .provider(user.getProvider().name())
                .build();
    }
}
