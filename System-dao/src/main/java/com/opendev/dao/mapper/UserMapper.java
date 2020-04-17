package com.opendev.dao.mapper;


import com.opendev.domian.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;


public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(@Param("username") String username);
}