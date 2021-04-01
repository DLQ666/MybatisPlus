package com.qi.mpdemo;

import com.qi.mpdemo.entity.User;
import com.qi.mpdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 *@program: mpdemo
 *@description:
 *@author: Hasee
 *@create: 2021-04-01 12:00
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCount() {
        int count = userService.count();
        System.out.println(count);
    }

    @Test
    public void testSaveBatch() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setAge(i);
            user.setName("hha"+i);
            users.add(user);
        }
        boolean b = userService.saveBatch(users);
        System.out.println("是否成功："+b);
    }
}
