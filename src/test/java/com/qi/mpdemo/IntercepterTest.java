package com.qi.mpdemo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.mpdemo.entity.Product;
import com.qi.mpdemo.entity.User;
import com.qi.mpdemo.mapper.ProductMapper;
import com.qi.mpdemo.mapper.UserMapper;
import com.qi.mpdemo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 *@program: mpdemo
 *@description:
 *@author: Hasee
 *@create: 2021-04-01 16:39
 */
@SpringBootTest
public class IntercepterTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage() {
        Page<User> pageParam = new Page<>(1,5);
        userMapper.selectPage(pageParam, null);
        //当前页码所有记录
        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);

        long total = pageParam.getTotal();
        System.out.println(total);

        boolean bn = pageParam.hasNext();
        System.out.println("下一页？"+ bn);

        boolean bp = pageParam.hasPrevious();
        System.out.println("上一页？"+ bp);
    }

    @Test
    public void testSelectByAge() {
        IPage<User> pageParam = userMapper.selectPageByPage(new Page<User>(1, 5), 3);
        List<User> records = pageParam.getRecords();
        records.forEach(System.out::println);
    }

    //测试乐观锁
    @Resource
    private ProductMapper productMapper;

    @Test
    public void testConcurrentUpdate() {

        //1、小李
        Product p1 = productMapper.selectById(1L);

        //2、小王
        Product p2 = productMapper.selectById(1L);

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);
        if (result2 == 0){
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            result2 = productMapper.updateById(p2);
        }
        System.out.println("小王修改重试结果：" + result2);

        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }
}
