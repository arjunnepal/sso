package com.custom.spring.sso.role;

import com.custom.spring.sso.common.PageResult;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:role:read')")
    public PageResult<RoleResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        return roleService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:role:read')")
    public ResponseEntity<RoleResource> get(@PathVariable String id) {
        logger.info("get {}", id);
        RoleResource role = roleService.get(id);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:role:create')")
    public ResponseEntity<RoleResource> create(@Valid @RequestBody RoleDTO role) {
        logger.info("create {}", role);
        RoleResource createdRole = roleService.create(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:role:update')")
    public ResponseEntity<RoleResource> update(@PathVariable String id, @Valid @RequestBody RoleDTO role) {
        logger.info("update {} role {}", id, role);
        RoleResource updatedRole = roleService.update(id, role);
        if (updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:role:delete')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("delete {}", id);
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
