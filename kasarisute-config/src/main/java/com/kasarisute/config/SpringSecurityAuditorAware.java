package com.kasarisute.config;

import java.lang.reflect.Method;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        try {


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof AnonymousAuthenticationToken) {
                throw new Exception("get user Authentication fail");
                // return Optional.of(Long.valueOf(0));
            }

            if (authentication == null) {
                throw new Exception("get user Authentication fail");

                // return Optional.of(Long.valueOf(0));
            }

            Object user = authentication.getPrincipal();

            if (user instanceof Long) {
                Long uid = (Long) user;
                return Optional.of(uid);
            }

            Class<?> userClass = user.getClass();

            String uidMethodName = "getUid";

            Method method_getUid = userClass.getMethod(uidMethodName);

            Long uid = (Long) method_getUid.invoke(user);

            return Optional.of(uid);

        } catch (Exception e) {
            
            return Optional.of(Long.valueOf(0));
        }
    }

}
