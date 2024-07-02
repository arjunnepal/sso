package com.custom.spring.sso.menu;

import com.custom.spring.sso.annotation.CurrentAuthentication;
import com.custom.spring.sso.common.PageResult;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;
    Logger logger = LoggerFactory.getLogger(getClass());

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:menu:read')")
    public PageResult<MenuResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        return menuService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:menu:read')")
    public ResponseEntity<MenuResource> get(@PathVariable String id) {
        logger.info("get {}", id);
        MenuResource menu = menuService.get(id);
        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:menu:create')")
    public ResponseEntity<MenuResource> create(@Valid @RequestBody MenuDTO menu) {
        logger.info("create {}", menu);
        MenuResource createdRole = menuService.create(menu);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:menu:update')")
    public ResponseEntity<MenuResource> update(@PathVariable String id, @Valid @RequestBody MenuDTO menu) {
        logger.info("update id {} menu {} ", id, menu);
        MenuResource updatedRole = menuService.update(id, menu);
        if (updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:menu:delete')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("delete {}", id);
        menuService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuResource> getAll(@AuthenticationPrincipal Authentication authentication) {
        logger.error("{}",authentication);
        return new ArrayList<>();
    }
}
