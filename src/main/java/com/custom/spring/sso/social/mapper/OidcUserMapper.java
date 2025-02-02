package com.custom.spring.sso.social.mapper;

import com.custom.spring.sso.user.CustomUserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface OidcUserMapper {
    OidcUser map(OidcUser oidcUser);

    OidcUser map(OidcIdToken idToken, OidcUserInfo userInfo, CustomUserDetails user);

}
