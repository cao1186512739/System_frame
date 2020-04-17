package com.opendev.service;

import com.github.pagehelper.PageInfo;


public interface MenuService {

    JsonResultDto<PageInfo<Menu>> get(Integer page, Integer limit);
}
