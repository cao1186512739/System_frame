package com.opendev.service;

import com.github.pagehelper.PageInfo;
import com.opendev.domian.Menu;
import com.opendev.response.ResponseHelper;


public interface MenuService {

    ResponseHelper<PageInfo<Menu>> get(Integer page, Integer limit);
}
