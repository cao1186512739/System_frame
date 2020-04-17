package com.opendev.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opendev.dao.mapper.MenuMapper;
import com.opendev.domian.Menu;
import com.opendev.response.ResponseHelper;
import com.opendev.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ResponseHelper get(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Menu> menus = menuMapper.selectAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menus);
        return ResponseHelper.Succeed(pageInfo);
    }
}
