package com.custom.spring.sso.user_permission;


import com.custom.spring.sso.permission.PermissionResource;

import java.util.List;
import java.util.Set;

public interface UserPermissionService {
    Set<String> getByUsername(String principal);

    void addPermissionsToUser(String id, Set<String> permissions);

    List<PermissionResource> getAllUserPermission(String id);
}
