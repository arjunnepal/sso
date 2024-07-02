package com.custom.spring.sso.user_role;

import com.custom.spring.sso.role.RoleResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Set<String> getRoleNamesByUsername(String username) {
        return this.userRoleRepository.findNameByUsername(username);
    }

    @Override
    public void addRolesToUser(String id, Set<String> roles) {
        userRoleRepository.deleteByUserId(id);
    }

    @Override
    public List<RoleResource> getAllRolesByUserId(String id) {
        return userRoleRepository.findAllRolesByUserId(id);
    }

    @Override
    public void saveAll(List<UserRole> userRoles) {
        userRoleRepository.saveAll(userRoles);
    }
}
