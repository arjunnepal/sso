package com.custom.spring.sso.permission;

import com.custom.spring.sso.common.PageResult;
import org.springframework.data.domain.Pageable;

public interface PermissionService {
    PageResult<PermissionResource> getAll(Pageable pageable);

    PermissionResource get(String id);

    PermissionResource create(PermissionDTO permission);

    PermissionResource update(String id, PermissionDTO permission);

    void delete(String id);
}
