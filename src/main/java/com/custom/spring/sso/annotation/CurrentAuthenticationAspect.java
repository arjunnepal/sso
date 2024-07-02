package com.custom.spring.sso.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CurrentAuthenticationAspect {

    @Before("@annotation(currentAuthentication)")
    public Authentication getCurrentAuthentication(CurrentAuthentication currentAuthentication) {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
