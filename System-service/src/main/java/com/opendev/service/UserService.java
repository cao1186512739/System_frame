package com.opendev.service;

import com.github.pagehelper.PageInfo;
import com.opendev.domian.Menu;
import com.opendev.domian.User;
import com.opendev.dto.InputUserDto;
import com.opendev.dto.OutputUserDto;
import com.opendev.response.ResponseHelper;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    ResponseHelper<PageInfo<User>> get(Integer page, Integer limit);

    ResponseHelper add(InputUserDto inputUserDto);

    ResponseHelper edit(InputUserDto inputUserDto);

    OutputUserDto getByUsername(String username);

    List<Menu> getMenus(String username);

    ResponseHelper login(HttpServletResponse response, String username, String password);
}
