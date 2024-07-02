package com.custom.spring.sso.menu;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MenuMapper {

    static BiFunction<MenuDTO, String, Menu> toEntity = (dto, id) -> Menu.builder()
            .name(dto.getName())
            .id(id)
            .url(dto.getUrl())
            .build();

    static Function<Menu, MenuResource> toResource = (menu) -> menu == null ? null :
            new MenuResource(menu.getId(),
                    menu.getName(),
                    menu.getUrl(),
                    MenuMapper.toResource.apply(menu.getParent()));
}