package com.qi.mpdemo;

import com.qi.mpdemo.entity.User;
import com.qi.mpdemo.mapper.UserMapper;
import com.qi.mpdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 *@program: mpdemo
 *@description:
 *@author: Hasee
 *@create: 2021-04-01 12:23
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> jone = userMapper.selectAllByName("Jone");
        jone.forEach(System.out::println);
    }
}
