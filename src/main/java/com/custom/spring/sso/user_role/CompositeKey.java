package com.custom.spring.sso.user_role;

import com.custom.spring.sso.role.Role;
import com.custom.spring.sso.user.User;
import lombok.Data;

@Data
public class CompositeKey {
    private Role role;
    private User user;
}
