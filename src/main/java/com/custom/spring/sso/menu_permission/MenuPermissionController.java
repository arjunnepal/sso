package com.custom.spring.sso.menu_permission;

import com.custom.spring.sso.permission.PermissionResource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/menus")
public class MenuPermissionController {
    private final MenuPermissionService menuPermissionService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MenuPermissionController(MenuPermissionService menuPermissionService) {
        this.menuPermissionService = menuPermissionService;
    }

    @PostMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('admin:menu-permission:update')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addPermissionToMenu(@PathVariable("id") String id
            , @Valid @RequestBody Set<String> permissions) {
        logger.info("add permissions {} to menu {}", permissions, id);
        menuPermissionService.addPermissionsToMenu(id, permissions);
    }

    @GetMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('admin:menu-permission:read')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PermissionResource> getAllMenuPermissions(@PathVariable("id") String id) {
        logger.info("get all menus {} permission", id);
        return menuPermissionService.getAllMenuPermission(id);
    }

}
