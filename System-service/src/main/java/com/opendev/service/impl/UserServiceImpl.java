package com.opendev.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opendev.component.FileComponent;
import com.opendev.component.RedisComponent;
import com.opendev.constant.PublicConstant;
import com.opendev.constant.ResultCode;
import com.opendev.dao.mapper.MenuMapper;
import com.opendev.dao.mapper.UserMapper;
import com.opendev.domian.Menu;
import com.opendev.domian.User;
import com.opendev.dto.InputUserDto;
import com.opendev.dto.OutputUserDto;
import com.opendev.response.ResponseHelper;
import com.opendev.service.UserService;
import com.opendev.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: 用户业务实现
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private FileComponent fileComponent;

    @Override
    public ResponseHelper<PageInfo<User>> get(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return ResponseHelper.Succeed(pageInfo);
    }

    @Override
    public ResponseHelper add(InputUserDto inputUserDto) {
        User user = BeanUtil.dtoToPo(inputUserDto, User.class);
        user.setAvatar(fileComponent.makeAvatar());
        user.setPassword(MD5Util.md5(user.getPassword()));
        return userMapper.insertSelective(user) > 0 ? ResponseHelper.Succeed() : ResponseHelper.Faild();
    }

    @Override
    public ResponseHelper edit(InputUserDto inputUserDto) {
        User user = BeanUtil.dtoToPo(inputUserDto, User.class);
        return userMapper.updateByPrimaryKeySelective(user) > 0 ? ResponseHelper.Succeed() : ResponseHelper.Faild();
    }

    @Override
    public OutputUserDto getByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return BeanUtil.poToDto(user, OutputUserDto.class);
    }

    /**
     * 获取当前登录用户拥有访问权限的菜单
     *
     * @param username
     * @return
     */
    @Override
    public List<Menu> getMenus(String username) {
        //生成为空key
        String key = username + PublicConstant.MENU_KEY;
        List<Menu> menus = (List<Menu>) redisComponent.getForListAll(key);
        if (null == menus || menus.isEmpty()) {
            menus = menuMapper.selectMenus(username);
            if (redisComponent.hasKey(key)) {
                redisComponent.del(key);
            }
            redisComponent.setForList(key, menus);
        }
        return menus;
    }

    /**
     * 用户登录业务实现
     *
     * @param response
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResponseHelper login(HttpServletResponse response, String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (StringUtil.isNull(user)) {
            return ResponseHelper.Faild(ResultCode.USER_NO_FOUND);
        }
        // 密码校验
        if (!MD5Util.verify(password, user.getPassword())) {
            return ResponseHelper.Faild(ResultCode.USERNAME_PASSWORD_ERROR);
        }
        // 生成token令牌
        String token = TokenUtil.makeToken(username);
        CookieUtil.set(response, PublicConstant.LOGIN_IDENTITY_KEY, token, false);
        return ResponseHelper.Succeed();
    }
}
