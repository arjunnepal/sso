package com.custom.spring.sso.consent;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;

import java.util.function.Function;

public class CustomAuthorizationConsentMapper {

    static Function<OAuth2AuthorizationConsent, CustomAuthorizationConsent> authConsentToEntity = (ac) ->
            ac == null ? null : CustomAuthorizationConsent.builder()
                    .authorities(AuthorityUtils.authorityListToSet(ac.getAuthorities()))
                    .principalName(ac.getPrincipalName())
                    .registrationClientId(ac.getRegisteredClientId())
                    .build();


    static Function<CustomAuthorizationConsent, OAuth2AuthorizationConsent> entityToAuthConsent = (ca) ->
            ca == null ? null : OAuth2AuthorizationConsent.withId(ca.getRegistrationClientId(), ca.getPrincipalName())
                    .authorities(a -> a.addAll(AuthorityUtils.createAuthorityList(ca.getAuthorities())))
                    .build();


}
