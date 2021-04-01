package com.qi.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.mpdemo.entity.eduCourse;
import com.qi.mpdemo.entity.User;
import com.qi.mpdemo.mapper.EduCourseMapper;
import com.qi.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MpdemoApplicationTests {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EduCourseMapper courseMapper;


    //查询user表所有数据
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加操作
    @Test
    void addUser() {
        User user = new User();
        user.setName("假数据点击按键231231");
        user.setAge(26);
        user.setEmail("aflafasfasfs@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
    }

    //修改操作
    @Test
    void updateUSer(){
        User user = new User();
        user.setId(1271373102342631426L);
        user.setAge(123);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //测试乐观锁
    @Test
    void testOptimisticLocker(){
//        //根据id查询数据
//        User user1 = userMapper.selectById(1271725417666887682L);
//        //查询
//        User user2 = userMapper.selectById(1271725417666887682L);
//        //用户1进行修改
//        user1.setAge(user1.getAge()+50);
//        //用户2修改数据
//        user2.setAge(user2.getAge()-30);
//
//
//        int result1 = userMapper.updateById(user1);
//        System.out.println("qwwwwwwwwwwwwwwwwww"+result1);
//        int result2 = userMapper.updateById(user2);
//        System.out.println("qweqweqweqweqw"+result2);
        BigDecimal num50 = new BigDecimal("50");
        BigDecimal num30 = new BigDecimal("30");
        eduCourse course1 = courseMapper.selectById(1281214246159507457L);
        eduCourse course2 = courseMapper.selectById(1281214246159507457L);

        course1.setPrice(course1.getPrice().add(num50));
        course2.setPrice(course2.getPrice().subtract(num30));
        int i = courseMapper.updateById(course1);
        System.out.println("1111111111111111111111111111111"+i);
        int i1 = courseMapper.updateById(course2);
        System.out.println("222222222222222222222222222222"+i1);
    }

    @Test
    public void testOptimisticLockerFail() {

        //查询
        User user = userMapper.selectById(1271725417666887682L);
        //修改数据
        user.setAge(user.getAge()-30);

        //模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
        //user.setVersion(user.getVersion());

        //执行更新
        userMapper.updateById(user);
    }

    //多个id批量查询
    @Test
    void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }

    @Test
    public void testSelectByMap(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

    //分页插件
    @Test
    void testPage(){
        //1、创建page对象
        //传入两个参数：当前页 和 每页记录数
        Page<User> page = new Page<>(1,3);
        //调用mp分页方法
        //调用mp分页插件过程中，底层封装
        //把分页所有数据封装到page对象里面
        userMapper.selectPage(page,null);
        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数

        System.out.println(page.hasNext());//下一页
        System.out.println(page.hasPrevious());//上一页

    }

    //删除操作  物理删除
    @Test
    void testDeleteById(){
        int result = userMapper.deleteById(1377516527355461637L);
        System.out.println(result);
    }

    //批量删除
    @Test
    void testDeleteList(){
        int result = userMapper.deleteBatchIds(Arrays.asList(1271414917729083394L,1271419849865609217L));
        System.out.println(result);
    }

    /**
     * 测试 逻辑删除
     */
    @Test
    public void testLogicDelete() {

        int result = userMapper.deleteById(1271650690365599745L);
        System.out.println(result);
        //测试
//        User user = userMapper.selectById(1271650690365599745L);
//        System.out.println(user);
    }

    //mp实现复杂查询操作
    @Test
    void testSelectQuery(){
        //创建对象
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //ge：>= 、gt：> 、le：<= 、 lt：<
        //查询age>=30的记录
        //第一个参数为字段名称    第二个是要设置的值比如上边的30
       /* wrapper.ge("age",30);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //eq：= 、ne：！=
        //wrapper.eq("name","Jone");
        //wrapper.ne("name","Jone");

        //between：范围中的值 、notBetween
        //wrapper.between("age",20,30);

        //like：模糊查询
        //wrapper.like("name","J");

        //orderByDesc：排序 例如根据id进行升序或者降序
        //wrapper.orderByDesc("id");

        //last：拼接
        //wrapper.last("limit 1");

        //指定要查询的列
        wrapper.select("id","name");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
}
