package com.qi.mpdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qi.mpdemo.entity.User;
import com.qi.mpdemo.mapper.UserMapper;
import com.qi.mpdemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 *@program: mpdemo
 *@description:
 *@author: Hasee
 *@create: 2021-04-01 11:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
