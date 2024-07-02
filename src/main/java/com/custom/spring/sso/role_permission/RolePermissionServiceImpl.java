package com.custom.spring.sso.role_permission;

import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.permission.PermissionResource;
import com.custom.spring.sso.role.Role;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    private final EntityManager entityManager;
    private final RolePermissionRepository rolePermissionRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository, EntityManager entityManager) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Set<String> getByRoles(Set<String> roles) {
        return rolePermissionRepository.findByRoles(roles);
    }

    @Override
    @Transactional
    public void addPermissionsToRole(String roleId, Set<String> permissionIds) {
        logger.info("Adding permissions to role {}", roleId);
        rolePermissionRepository.deleteByRoleId(roleId);
        List<RolePermissions> allRolePermission = permissionIds.stream().map(id -> new RolePermissions(entityManager.getReference(Role.class, roleId),
                entityManager.getReference(Permission.class, id))).toList();
        rolePermissionRepository.saveAll(allRolePermission);
    }

    @Override
    public List<PermissionResource> getAllRolePermission(String roleId) {
        logger.info("Getting all permissions for role {}", roleId);
        return rolePermissionRepository.findByRoleId(roleId);
    }
}
