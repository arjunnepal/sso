package com.custom.spring.sso.user;

import com.custom.spring.sso.common.PageResult;
import com.custom.spring.sso.role_permission.RolePermissionService;
import com.custom.spring.sso.social.mapper.OidcUserMapper;
import com.custom.spring.sso.user_permission.UserPermissionService;
import com.custom.spring.sso.user_role.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl extends OidcUserService implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final UserPermissionService userPermissionService;
    private final RolePermissionService rolePermissionService;
    private final Map<String, OidcUserMapper> mappers;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, UserPermissionService userPermissionService, RolePermissionService rolePermissionService, Map<String,OidcUserMapper> mappers) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.userPermissionService = userPermissionService;
        this.rolePermissionService = rolePermissionService;
        this.mappers = mappers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername {}", username);
        CustomUserDetails customUserDetails = this.userRepository.findByUsername(username).map(UserMapper.mapToCustomUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Set<String> roles = userRoleService.getRoleNamesByUsername(username);
        Set<String> userPermission = userPermissionService.getByUsername(username);
        Set<String> rolePermission = rolePermissionService.getByRoles(roles);
        rolePermission.addAll(userPermission);
        customUserDetails.setAuthorities(AuthorityUtils.createAuthorityList(rolePermission));
        return customUserDetails;
    }

    @Override
    public PageResult<UserResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        Page<User> page = userRepository.findAll(pageable);
        return new PageResult<>(page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.get().map(UserMapper.toResource).toList());
    }

    @Override
    public UserResource get(String id) {
        logger.info("get {}", id);
        return userRepository.findById(id).map(UserMapper.toResource).orElse(null);
    }

    @Override
    public UserResource create(UserDTO user) {
        logger.info("create {}", user);
        return UserMapper.toResource.apply(userRepository.save(UserMapper.toEntity.apply(user, null)));
    }

    @Override
    public UserResource update(String id, UserDTO user) {
        logger.info("update {}", id);
        return UserMapper.toResource.apply(userRepository.save(UserMapper.toEntity.apply(user, id)));

    }

    @Override
    public void delete(String id) {
        logger.info("delete {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Assert.isTrue(mappers.containsKey(registrationId), "No mapper defined for such registrationId");
        OidcUserMapper mapper = mappers.get(userRequest.getClientRegistration().getRegistrationId());
        String email = userRequest.getIdToken().getEmail();
        CustomUserDetails localUser = (CustomUserDetails) loadUserByUsername(email);
        if (localUser != null) {
            return mapper.map(oidcUser.getIdToken(), oidcUser.getUserInfo(), localUser);
        }
        return mapper.map(oidcUser);
    }
}
