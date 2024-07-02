package com.custom.spring.sso.role_permission;

import com.custom.spring.sso.permission.PermissionResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RolePermissionRepository extends JpaRepository<RolePermissions, CompositeKey> {
    @Query("select rp.permission.name from RolePermissions rp where rp.role.name in :roles")
    Set<String> findByRoles(@Param(value = "roles") Set<String> roles);

    @Query("select new com.custom.spring.sso.permission.PermissionResource(rp.permission.id,rp.permission.name,rp.permission.description)" +
            " from RolePermissions rp where rp.role.id = :roleId")
    List<PermissionResource> findByRoleId(@Param("roleId") String roleId);

    void deleteByRoleId(String roleId);
}
