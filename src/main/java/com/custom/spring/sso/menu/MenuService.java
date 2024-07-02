package com.custom.spring.sso.menu;

import com.custom.spring.sso.common.PageResult;
import org.springframework.data.domain.Pageable;

public interface MenuService {
    PageResult<MenuResource> getAll(Pageable pageable);

    MenuResource get(String id);

    MenuResource create(MenuDTO menu);

    MenuResource update(String id, MenuDTO menu);

    void delete(String id);
}
