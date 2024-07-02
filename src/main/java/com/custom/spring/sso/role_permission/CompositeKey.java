package com.custom.spring.sso.role_permission;

import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.role.Role;
import lombok.Data;

@Data
public class CompositeKey {
    private Role role;
    private Permission permission;
}
