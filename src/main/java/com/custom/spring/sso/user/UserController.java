package com.custom.spring.sso.user;

import com.custom.spring.sso.common.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResult<UserResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return userService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:user:read')")
    public ResponseEntity<UserResource> get(@PathVariable String id) {
        logger.info("get {}", id);
        UserResource user = userService.get(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:user:create')")
    public ResponseEntity<UserResource> create(@RequestBody UserDTO user) {
        logger.info("create {}", user.getUsername());
        UserResource createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:user:update')")
    public ResponseEntity<UserResource> update(@PathVariable String id, @RequestBody UserDTO user) {
        logger.info("update {} username {}", id, user.getUsername());
        UserResource updatedUser = userService.update(id, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:user:delete')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("delete {}", id);
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
