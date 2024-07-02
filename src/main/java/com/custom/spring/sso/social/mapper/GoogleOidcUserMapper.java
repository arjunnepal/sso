package com.custom.spring.sso.social.mapper;

import com.custom.spring.sso.social.CustomOidcUser;
import com.custom.spring.sso.user.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("google")
public class GoogleOidcUserMapper implements OidcUserMapper {

    public OidcUser map(OidcUser oidcUser) {
        CustomOidcUser user = new CustomOidcUser(oidcUser.getIdToken(), oidcUser.getUserInfo());
        user.setUsername(oidcUser.getEmail());
        user.setActive(true);
        return user;
    }

    public OidcUser map(OidcIdToken idToken, OidcUserInfo userInfo, CustomUserDetails user) {
        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());

        Map<String, Object> claims = new HashMap<>();
        claims.putAll(idToken.getClaims());

        OidcIdToken customIdToken = new OidcIdToken(
                idToken.getTokenValue(), idToken.getIssuedAt(), idToken.getExpiresAt(), claims
        );

        CustomOidcUser oidcUser = new CustomOidcUser(authorities, customIdToken, userInfo);
        oidcUser.setId(UUID.fromString(user.getId()));
        oidcUser.setUsername(user.getUsername());
        oidcUser.setCreatedAt(user.getCreatedAt());
        oidcUser.setActive(user.isEnabled() &&
                user.isAccountNonLocked() &&
                user.isAccountNonExpired() &&
                oidcUser.isCredentialsNonExpired());
        oidcUser.setFirstName(user.getFirstName());
        oidcUser.setMiddleName(user.getMiddleName());
        oidcUser.setLastName(user.getLastName());
        return oidcUser;
    }
}
