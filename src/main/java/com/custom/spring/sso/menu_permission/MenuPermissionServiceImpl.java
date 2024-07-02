package com.custom.spring.sso.menu_permission;

import com.custom.spring.sso.menu.Menu;
import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.permission.PermissionResource;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MenuPermissionServiceImpl implements MenuPermissionService {
    private final EntityManager entityManager;
    private final com.custom.spring.sso.menu_permission.MenuPermissionRepository menuPermissionRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public MenuPermissionServiceImpl(MenuPermissionRepository menuPermissionRepository, EntityManager entityManager) {
        this.menuPermissionRepository = menuPermissionRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Set<String> getByMenus(Set<String> menus) {
        return menuPermissionRepository.findByMenus(menus);
    }

    @Override
    @Transactional
    public void addPermissionsToMenu(String menuId, Set<String> permissionIds) {
        logger.info("Adding permissions to menu {}", menuId);
        menuPermissionRepository.deleteByMenuId(menuId);
        List<MenuPermissions> allMenuPermission = permissionIds.stream().map(id -> new MenuPermissions(entityManager.getReference(Menu.class, menuId),
                entityManager.getReference(Permission.class, id))).toList();
        menuPermissionRepository.saveAll(allMenuPermission);
    }

    @Override
    public List<PermissionResource> getAllMenuPermission(String menuId) {
        logger.info("Getting all permissions for menu {}", menuId);
        return menuPermissionRepository.findByMenuId(menuId);
    }
}
