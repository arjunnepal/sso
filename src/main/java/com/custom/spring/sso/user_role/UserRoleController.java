package com.custom.spring.sso.user_role;

import com.custom.spring.sso.role.RoleResource;
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
public class UserRoleController {
    private final UserRoleService userRoleService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('admin:user-role:update')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addRoleUser(@PathVariable("id") String id
            , @Valid @RequestBody Set<String> roles) {
        logger.info("add roles {} to id {}", roles, id);
        userRoleService.addRolesToUser(id, roles);
    }

    @GetMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('admin:user-role:read')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<RoleResource> getAllUserRoles(@PathVariable("id") String id) {
        logger.info("get all roles {} roles", id);
        return userRoleService.getAllRolesByUserId(id);
    }

}
