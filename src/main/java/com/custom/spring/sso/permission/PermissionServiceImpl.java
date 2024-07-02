package com.custom.spring.sso.permission;

import com.custom.spring.sso.common.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    Logger logger = LoggerFactory.getLogger(getClass());


    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PageResult<PermissionResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        Page<Permission> page = permissionRepository.findAll(pageable);
        return new PageResult<>(page.getTotalElements(), page.getSize(), page.getNumber(), page.get().map(PermissionMapper.toResource).toList());
    }

    @Override
    public PermissionResource get(String id) {
        logger.info("get {}", id);
        return permissionRepository.findById(id).map(PermissionMapper.toResource).orElse(null);
    }

    @Override
    public PermissionResource create(PermissionDTO permission) {
        logger.info("create {}", permission);
        return PermissionMapper.toResource.apply(permissionRepository.save(PermissionMapper.toEntity.apply(permission, null)));
    }

    @Override
    public PermissionResource update(String id, PermissionDTO permission) {
        logger.info("update {} permission {}", id, permission);
        return PermissionMapper.toResource.apply(permissionRepository.save(PermissionMapper.toEntity.apply(permission, id)));

    }

    @Override
    public void delete(String id) {
        logger.info("delete {}", id);
        permissionRepository.deleteById(id);
    }
}
