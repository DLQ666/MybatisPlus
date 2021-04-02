package com.qi.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qi.mpdemo.entity.User;
import com.qi.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *@program: mpdemo
 *@description:
 *@author: Hasee
 *@create: 2021-04-01 22:20
 */
@SpringBootTest
public class WrapperTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
     */
    @Test
    public void testQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //column：对应数据库表中的列名
        queryWrapper
                .like("name", "h")
                .between("age", 1, 70)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test() {
        String[] strs = new String[]{"1", "2", "3", "4", "5", "6"};
        System.out.println(strs.length >> 1);
        System.out.println(strs[1]);
        List<String> list = new ArrayList<>(10);
        for (int i = 0; i < strs.length >> 1; i++) {
            System.out.println(strs[i]);
            list.add(strs[i]);
        }
        System.out.println(list);
    }
}
