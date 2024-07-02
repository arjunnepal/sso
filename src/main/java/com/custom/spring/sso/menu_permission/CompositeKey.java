package com.custom.spring.sso.menu_permission;

import com.custom.spring.sso.menu.Menu;
import com.custom.spring.sso.permission.Permission;
import lombok.Data;

@Data
public class CompositeKey {
    private Menu menu;
    private Permission permission;
}
