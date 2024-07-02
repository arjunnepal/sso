package com.custom.spring.sso.role_permission;

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
@RequestMapping("/roles")
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @PostMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('admin:role-permission:update')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addPermissionToRole(@PathVariable("id") String id
            , @Valid @RequestBody Set<String> permissions) {
        logger.info("add permissions {} to role {}", permissions, id);
        rolePermissionService.addPermissionsToRole(id, permissions);
    }

    @GetMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('admin:role-permission:read')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PermissionResource> getAllRolePermissions(@PathVariable("id") String id) {
        logger.info("get all roles {} permission", id);
        return rolePermissionService.getAllRolePermission(id);
    }

}
