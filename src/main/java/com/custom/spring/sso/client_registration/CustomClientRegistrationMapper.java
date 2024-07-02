package com.custom.spring.sso.client_registration;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.function.Function;

public class CustomClientRegistrationMapper {

    static Function<CustomClientRegistration, ClientRegistration> toClientRegistration = ccr ->
            ClientRegistration.withRegistrationId(ccr.getId())
                    .clientId(ccr.getClientId())
                    .clientSecret(ccr.getClientSecret())
                    .clientName(ccr.getClientName())
                    .authorizationGrantType(new AuthorizationGrantType(ccr.getAuthorizationGrantType()))
                    .clientAuthenticationMethod(new ClientAuthenticationMethod(ccr.getClientAuthenticationMethod()))
                    .redirectUri(ccr.getRedirectUri())
                    .scope(ccr.getScopes())
                    .authorizationUri(ccr.getProviderAuthorizationUri())
                    .tokenUri(ccr.getProviderTokenUri())
                    .issuerUri(ccr.getProviderIssuerUri())
                    .jwkSetUri(ccr.getProviderJwkSetUri())
                    .userInfoUri(ccr.getProviderUserInfoUri())
                    .userNameAttributeName(ccr.getProviderUserNameAttributeName())
                    .providerConfigurationMetadata(ccr.getProviderConfigurationMetadata())
                    .userInfoAuthenticationMethod(
                            ccr.getProviderUserInfoAuthenticationMethod() == null ? null :
                                    new AuthenticationMethod(ccr.getProviderUserInfoAuthenticationMethod()))
                    .build();
}
