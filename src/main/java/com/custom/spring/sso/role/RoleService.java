package com.custom.spring.sso.role;

import com.custom.spring.sso.common.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    PageResult<RoleResource> getAll(Pageable pageable);

    RoleResource get(String id);

    RoleResource create(RoleDTO role);

    RoleResource update(String id, RoleDTO role);

    void delete(String id);

    List<RoleResource> getDefaultRoles();

}
