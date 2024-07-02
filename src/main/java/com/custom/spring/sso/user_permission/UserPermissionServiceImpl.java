package com.custom.spring.sso.user_permission;

import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.permission.PermissionResource;
import com.custom.spring.sso.user.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    private final UserPermissionRepository userPermissionRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EntityManager entityManager;

    public UserPermissionServiceImpl(UserPermissionRepository userPermissionRepository, EntityManager entityManager) {
        this.userPermissionRepository = userPermissionRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Set<String> getByUsername(String username) {
        logger.info("getByUsername {}", username);
        return userPermissionRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void addPermissionsToUser(String userId, Set<String> permissionIds) {
        logger.info("Adding permissions to user {}", userId);
        userPermissionRepository.deleteByUserId(userId);
        List<UserPermission> allRolePermission = permissionIds.stream().map(id -> new UserPermission(
                entityManager.getReference(User.class, userId),
                entityManager.getReference(Permission.class, id))).toList();
        userPermissionRepository.saveAll(allRolePermission);
    }

    @Override
    public List<PermissionResource> getAllUserPermission(String userId) {
        logger.info("Getting all permissions for user {}", userId);
        return userPermissionRepository.findByUserId(userId);
    }
}
