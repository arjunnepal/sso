package com.custom.spring.sso.config;

import com.custom.spring.sso.jose.Jwks;
import com.custom.spring.sso.social.CustomOidcUser;
import com.custom.spring.sso.user.CustomUserDetails;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.token.*;

import java.util.stream.Collectors;

@Configuration(proxyBeanMethods = false)
public class TokenConfiguration {

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator(
            JWKSource<SecurityContext> jwkSource,
            OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer
    ) {
        NimbusJwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource);
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        jwtGenerator.setJwtCustomizer(jwtTokenCustomizer);
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();

        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator, accessTokenGenerator, refreshTokenGenerator
        );
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return context -> {
            if (context.getPrincipal().getPrincipal() instanceof CustomUserDetails principal) {
                context.getClaims()
                        .claim(
                                "authorities",
                                principal.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toSet())
                        );
                context.getClaims().claim("firstName", principal.getFirstName());
                if (principal.getMiddleName() != null && !principal.getMiddleName().isBlank()) {
                    context.getClaims().claim("middleName", principal.getMiddleName());
                }
                context.getClaims().claim("lastName", principal.getLastName());
            }

            if (context.getPrincipal().getPrincipal() instanceof CustomOidcUser principal) {
                context.getClaims()
                        .claim(
                                "authorities",
                                principal.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toSet())
                        );
                context.getClaims().claim("firstName", principal.getFirstName());
                if (principal.getMiddleName() != null && !principal.getMiddleName().isBlank()) {
                    context.getClaims().claim("middleName", principal.getMiddleName());
                }
                context.getClaims().claim("lastName", principal.getLastName());
            }
        };
    }
}