package com.custom.spring.sso.registered_client;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomRegisteredClientMapper {
    static Function<RegisteredClient, CustomRegisteredClient> registeredClientToEntity =
            (rc) -> CustomRegisteredClient
                    .builder()
                    .clientId(rc.getClientId())
                    .clientSecret(rc.getClientSecret())
                    .clientName(rc.getClientName())
                    .clientIdIssuedAt(rc.getClientIdIssuedAt())
                    .clientSecretExpiresAt(rc.getClientSecretExpiresAt())
                    .scopes(rc.getScopes())
                    .redirectUris(rc.getRedirectUris())
                    .postLogoutRedirectUris(rc.getPostLogoutRedirectUris())
                    .authorizationGrantTypes(rc.getAuthorizationGrantTypes()
                            .stream()
                            .map(AuthorizationGrantType::getValue)
                            .toList())
                    .clientAuthenticationMethods(rc.getClientAuthenticationMethods()
                            .stream()
                            .map(ClientAuthenticationMethod::getValue).toList())
                    .clientSettings(rc.getClientSettings().getSettings()
                            .keySet()
                            .stream()
                            .filter(k -> rc.getClientSettings().getSetting(k) != null)
                            .collect(Collectors.toMap(Function.identity(), k -> rc.getClientSettings().getSetting(k))))
                    .tokenSettings(rc.getTokenSettings().getSettings()
                            .keySet()
                            .stream()
                            .filter(k -> rc.getTokenSettings().getSetting(k) != null)
                            .collect(Collectors.toMap(Function.identity(), k -> rc.getTokenSettings().getSetting(k))))
                    .id(rc.getId())
                    .build();

    static Function<CustomRegisteredClient, RegisteredClient> toRegisteredClient =
            (rc) -> RegisteredClient
                    .withId(rc.getId())
                    .clientId(rc.getClientId())
                    .clientSecret(rc.getClientSecret())
                    .clientName(rc.getClientName())
                    .clientIdIssuedAt(rc.getClientIdIssuedAt())
                    .clientSecretExpiresAt(rc.getClientSecretExpiresAt())
                    .scopes(sc -> sc.addAll(rc.getScopes()))
                    .redirectUris(ru -> ru.addAll(rc.getRedirectUris()))
                    .postLogoutRedirectUris(lu -> lu.addAll(rc.getPostLogoutRedirectUris()))
                    .authorizationGrantTypes(agt -> agt.addAll(rc.getAuthorizationGrantTypes()
                            .stream()
                            .map(AuthorizationGrantType::new)
                            .toList()))
                    .clientAuthenticationMethods(cam -> cam.addAll(rc.getClientAuthenticationMethods()
                            .stream()
                            .map(ClientAuthenticationMethod::new).toList()))
                    .clientSettings(rc.getClientSettings() == null ? ClientSettings.builder().build() : ClientSettings.withSettings(rc.getClientSettings()).build())
                    .tokenSettings(rc.getTokenSettings() == null ? TokenSettings.builder().build() : TokenSettings.withSettings(rc.getClientSettings()).build())
                    .build();
}
