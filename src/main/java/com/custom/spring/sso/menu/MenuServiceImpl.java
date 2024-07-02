package com.custom.spring.sso.menu;

import com.custom.spring.sso.common.PageResult;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final EntityManager entityManager;
    Logger logger = LoggerFactory.getLogger(getClass());


    public MenuServiceImpl(MenuRepository menuRepository, EntityManager entityManager) {
        this.menuRepository = menuRepository;
        this.entityManager = entityManager;
    }

    @Override
    public PageResult<MenuResource> getAll(Pageable pageable) {
        logger.info("getAll {}", pageable);
        Page<Menu> page = menuRepository.findAll(pageable);
        return new PageResult<>(page.getTotalElements(), page.getSize(), page.getNumber(), page.get().map(MenuMapper.toResource).toList());
    }

    @Override
    public MenuResource get(String id) {
        logger.info("get {}", id);
        return menuRepository.findById(id).map(MenuMapper.toResource).orElse(null);
    }

    @Override
    public MenuResource create(MenuDTO menu) {
        logger.info("create {}", menu);
        Menu entity = MenuMapper.toEntity.apply(menu, null);
        entity.setParent(entityManager.getReference(Menu.class, menu.getParentId()));
        return MenuMapper.toResource.apply(menuRepository.save(entity));
    }

    @Override
    public MenuResource update(String id, MenuDTO menu) {
        logger.info("update {} menu {}", id, menu);
        Menu entity = MenuMapper.toEntity.apply(menu, id);
        entity.setParent(entityManager.getReference(Menu.class, menu.getParentId()));
        return MenuMapper.toResource.apply(menuRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        logger.info("delete {}", id);
        menuRepository.deleteById(id);
    }


}
