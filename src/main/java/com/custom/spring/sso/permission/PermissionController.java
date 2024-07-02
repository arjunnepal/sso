package com.custom.spring.sso.permission;

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
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;
    Logger logger = LoggerFactory.getLogger(getClass());

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:permission:read')")
    public PageResult<PermissionResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        return permissionService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:permission:read')")
    public ResponseEntity<PermissionResource> get(@PathVariable String id) {
        logger.info("get {}", id);
        PermissionResource permission = permissionService.get(id);
        if (permission != null) {
            return new ResponseEntity<>(permission, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:permission:create')")
    public ResponseEntity<PermissionResource> create(@Valid @RequestBody PermissionDTO permission) {
        logger.info("create {}", permission);
        PermissionResource createdRole = permissionService.create(permission);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:permission:update')")
    public ResponseEntity<PermissionResource> update(@PathVariable String id, @Valid @RequestBody PermissionDTO permission) {
        logger.info("update id {} permission {} ", id, permission);
        PermissionResource updatedRole = permissionService.update(id, permission);
        if (updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("delete {}", id);
        permissionService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
