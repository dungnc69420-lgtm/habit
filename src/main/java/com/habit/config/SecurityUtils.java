package com.habit.config;

import com.habit.security.UserPrincipal;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public final class SecurityUtils {

    public static UserPrincipal getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal principal) {
            return principal;
        }
        throw new IllegalStateException("No authenticated user in context");
    }
    
    public static String getCurrentUserName() {
        return getCurrentUser().getName();
    }
    
    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}