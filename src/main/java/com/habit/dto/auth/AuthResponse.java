package com.habit.dto.auth;

import lombok.Data;

@Data
@lombok.Builder
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String email;
    private String avatarUrl;
    private String provider;
}
