package com.custom.spring.sso.user_role;

import com.custom.spring.sso.role.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, CompositeKey> {

    @Query("select ur.role.name from UserRole ur where ur.user.username =:username")
    Set<String> findNameByUsername(@Param("username") String username);

    void deleteByUserId(String id);

    @Query("select new com.custom.spring.sso.role.RoleResource(ur.role.id,ur.role.name,ur.role.description)" +
            " from UserRole ur where ur.user.id = :userId")
    List<RoleResource> findAllRolesByUserId(@Param("userId") String userId);
}
