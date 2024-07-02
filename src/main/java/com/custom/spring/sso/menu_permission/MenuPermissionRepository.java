package com.custom.spring.sso.menu_permission;

import com.custom.spring.sso.permission.PermissionResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MenuPermissionRepository extends JpaRepository<MenuPermissions, CompositeKey> {
    @Query("select mp.permission.name from MenuPermissions mp where mp.menu.name in :menus")
    Set<String> findByMenus(@Param(value = "menus") Set<String> menus);

    @Query("select new com.custom.spring.sso.permission.PermissionResource(mp.permission.id,mp.permission.name,mp.permission.description)" +
            " from MenuPermissions mp where mp.menu.id = :menuId")
    List<PermissionResource> findByMenuId(@Param("menuId") String menuId);

    void deleteByMenuId(String menuId);
}
