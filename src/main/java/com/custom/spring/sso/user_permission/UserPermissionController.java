package com.custom.spring.sso.user_permission;

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
@RequestMapping("/users")
public class UserPermissionController {
    private final UserPermissionService userPermissionService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @PostMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('admin:user-permission:update')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addPermissionToRole(@PathVariable("id") String id
            , @Valid @RequestBody Set<String> permissions) {
        logger.info("add permissions {} to user {}", permissions, id);
        userPermissionService.addPermissionsToUser(id, permissions);
    }

    @GetMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('admin:user-permission:read')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PermissionResource> getAllRolePermissions(@PathVariable("id") String id) {
        logger.info("get all user {} permissions", id);
        return userPermissionService.getAllUserPermission(id);
    }

}
