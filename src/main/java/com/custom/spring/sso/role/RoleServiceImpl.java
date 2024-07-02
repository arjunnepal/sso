package com.custom.spring.sso.role;

import com.custom.spring.sso.common.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public PageResult<RoleResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        Page<Role> page = roleRepository.findAll(pageable);
        return new PageResult<>(page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.get().map(RoleMapper.toResource).toList());
    }

    @Override
    public RoleResource get(String id) {
        logger.info("get {}", id);
        return roleRepository.findById(id).map(RoleMapper.toResource).orElse(null);
    }

    @Override
    public RoleResource create(RoleDTO role) {
        logger.info("create {}", role);
        return RoleMapper.toResource.apply(roleRepository.save(RoleMapper.toEntity.apply(role, null)));
    }

    @Override
    public RoleResource update(String id, RoleDTO role) {
        logger.info("update {}", id);
        return RoleMapper.toResource.apply(roleRepository.save(RoleMapper.toEntity.apply(role, id)));

    }

    @Override
    public void delete(String id) {
        logger.info("delete {}", id);
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleResource> getDefaultRoles() {
        return roleRepository.findAllDefaultRoles().stream().map(RoleMapper.toResource).toList();
    }

}
