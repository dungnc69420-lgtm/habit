package com.habit.security;

import com.habit.model.User;
import com.habit.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    
    @Value("${app.frontend-url}")
    private String frontendUrl;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attrs = oAuth2User.getAttributes();
        
        String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String providerId = (String) attrs.get("sub");
        String avatarUrl = (String) attrs.get("picture");
        
        // Find existing or register new user
        User user = userRepository.findByProviderIdAndProvider(providerId, User.AuthProvider.GOOGLE)
                .orElseGet(() -> userRepository.findByEmail(email)
                        .map(existing -> {
                            // Link Google to an existing manual account
                            existing.setProvider(User.AuthProvider.GOOGLE);
                            existing.setProviderId(providerId);
                            existing.setAvatarUrl(avatarUrl);
                            existing.setEmailVerified(true);
                            return userRepository.save(existing);
                        })
                        .orElseGet(() -> userRepository.save(User.builder()
                                .email(email)
                                .name(name)
                                .avatarUrl(avatarUrl)
                                .provider(User.AuthProvider.GOOGLE)
                                .providerId(providerId)
                                .emailVerified(true)
                                .build()))
                );
        
        String token = tokenProvider.generateToken(user.getEmail());
        
        String redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/callback")
                .queryParam("token", token)
                .queryParam("email", email)
                .build().toUriString();
        
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
