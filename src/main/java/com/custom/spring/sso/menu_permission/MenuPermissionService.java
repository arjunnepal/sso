package com.custom.spring.sso.menu_permission;


import com.custom.spring.sso.permission.PermissionResource;

import java.util.List;
import java.util.Set;

public interface MenuPermissionService {
    Set<String> getByMenus(Set<String> menus);

    void addPermissionsToMenu(String menuId, Set<String> permissions);

    List<PermissionResource> getAllMenuPermission(String menuId);
}
