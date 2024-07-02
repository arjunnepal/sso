package com.custom.spring.sso.user_permission;

import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.user.User;
import lombok.Data;

@Data
public class CompositeKey {
    private User user;
    private Permission permission;
}
