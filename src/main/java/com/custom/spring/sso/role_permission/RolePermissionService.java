package com.custom.spring.sso.role_permission;


import com.custom.spring.sso.permission.PermissionResource;

import java.util.List;
import java.util.Set;

public interface RolePermissionService {
    Set<String> getByRoles(Set<String> roles);

    void addPermissionsToRole(String roleId, Set<String> permissions);

    List<PermissionResource> getAllRolePermission(String roleId);
}
