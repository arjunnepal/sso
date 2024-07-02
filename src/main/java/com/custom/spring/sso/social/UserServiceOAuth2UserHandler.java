package com.custom.spring.sso.social;

import com.custom.spring.sso.role.Role;
import com.custom.spring.sso.role.RoleResource;
import com.custom.spring.sso.role.RoleService;
import com.custom.spring.sso.user.User;
import com.custom.spring.sso.user.UserDTO;
import com.custom.spring.sso.user.UserResource;
import com.custom.spring.sso.user.UserService;
import com.custom.spring.sso.user_role.UserRole;
import com.custom.spring.sso.user_role.UserRoleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class UserServiceOAuth2UserHandler implements Consumer<OidcUser> {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final EntityManager entityManager;

    @Override
    public void accept(OidcUser oidcUser) {
        // Capture oidcUser in a local data store on first authentication
        User user = this.userService.getByUsername(oidcUser.getEmail());
        if (user == null) {
            UserResource userResource = this.userService.create(new UserDTO(oidcUser.getName(),
                    oidcUser.getGivenName(),
                    oidcUser.getMiddleName(),
                    oidcUser.getFamilyName()));

            List<RoleResource> defaultRoles = roleService.getDefaultRoles();


            if (defaultRoles == null || defaultRoles.isEmpty()) {
                return;
            }
            List<UserRole> userRoles = new ArrayList<>();
            defaultRoles.forEach(r -> {
                UserRole userRole = new UserRole();
                userRole.setUser(entityManager.getReference(User.class, userResource.getId()));
                userRole.setRole(entityManager.getReference(Role.class, r.getId()));
                userRoles.add(userRole);
            });
            userRoleService.saveAll(userRoles);
        }
    }
}