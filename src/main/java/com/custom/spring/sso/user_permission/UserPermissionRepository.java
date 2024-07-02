package com.custom.spring.sso.user_permission;

import com.custom.spring.sso.permission.PermissionResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserPermissionRepository extends JpaRepository<UserPermission, CompositeKey> {

    @Query("select up.permission.name from UserPermission up where up.user.username = :username")
    Set<String> findByUsername(@Param("username") String username);

    void deleteByUserId(String userId);

    @Query("select new com.custom.spring.sso.permission.PermissionResource(up.permission.id,up.permission.name,up.permission.description)" +
            " from UserPermission up where up.user.id = :userId")
    List<PermissionResource> findByUserId(@Param("userId") String userId);
}
