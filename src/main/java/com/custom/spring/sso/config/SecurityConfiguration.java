package com.custom.spring.sso.config;

import com.custom.spring.sso.registered_client.CustomRegisteredClientService;
import com.custom.spring.sso.social.SocialLoginAuthenticationSuccessHandler;
import com.custom.spring.sso.social.UserServiceOAuth2UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return
                http.cors(Customizer.withDefaults())
                        .authorizeHttpRequests(authorize ->
                                authorize
                                        .requestMatchers("/assets/**", "/login").permitAll()
                                        .anyRequest().authenticated()
                        ).formLogin(formLogin -> formLogin.loginPage("/login"))
                        .oauth2Login(oauth2Login ->
                                oauth2Login
                                        .loginPage("/login")
                                        .successHandler(authenticationSuccessHandler)

                        )
                        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                        .logout(LogoutConfigurer::permitAll)
                        .build();

    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(UserServiceOAuth2UserHandler handler) {
        SocialLoginAuthenticationSuccessHandler authenticationSuccessHandler =
                new SocialLoginAuthenticationSuccessHandler();
        authenticationSuccessHandler.setOidcUserHandler(handler);
        return authenticationSuccessHandler;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(CustomRegisteredClientService customRegisteredClientService) {
        List<String> allOriginUrls = customRegisteredClientService.findAllOriginUrls();
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(allOriginUrls);
        configuration.setAllowedMethods(List.of(RequestMethod.GET.name(),
                RequestMethod.POST.name(),
                RequestMethod.PUT.name(),
                RequestMethod.DELETE.name(),
                RequestMethod.OPTIONS.name()));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
