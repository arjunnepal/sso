package com.custom.spring.sso.user_role;


import com.custom.spring.sso.role.RoleResource;

import java.util.List;
import java.util.Set;

public interface UserRoleService {
    Set<String> getRoleNamesByUsername(String username);

    void addRolesToUser(String id, Set<String> roles);

    List<RoleResource> getAllRolesByUserId(String id);

    void saveAll(List<UserRole> userRoles);
}
