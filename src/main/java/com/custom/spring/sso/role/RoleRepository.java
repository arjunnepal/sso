package com.custom.spring.sso.role;

import com.custom.spring.sso.common.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends AbstractRepository<Role> {
    @Query("select r from Role r where r.defaultRole = true ")
    List<Role> findAllDefaultRoles();
}
