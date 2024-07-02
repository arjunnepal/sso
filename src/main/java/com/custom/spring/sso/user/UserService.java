package com.custom.spring.sso.user;

import com.custom.spring.sso.common.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

public interface UserService extends UserDetailsService {

    PageResult<UserResource> getAll(Pageable pageable);

    UserResource get(String id);

    UserResource create(UserDTO user);

    UserResource update(String id, UserDTO user);

    void delete(String id);

    User getByUsername(String name);
}
