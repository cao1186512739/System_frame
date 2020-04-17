package com.opendev.service.impl;

import com.github.pagehelper.PageHelper;
import com.opendev.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public JsonResultDto<PageInfo<Menu>> get(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Menu> menus = menuMapper.selectAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menus);
        return success(pageInfo);
    }
}
