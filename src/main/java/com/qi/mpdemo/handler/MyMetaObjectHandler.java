package com.qi.mpdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //使用mp实现添加操作，这个方法执行
    @Override
    public void insertFill(MetaObject metaObject) {
        //实现填充业务
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        //判断当前对象的自动填充属性是否已经进行了赋值
        Object age = this.getFieldValByName("age", metaObject);
        if (age == null){
            this.strictInsertFill(metaObject, "age", Integer.class, 10);
        }

        //判断当前的对象的自动填充属性是否包含当前属性
        boolean author = metaObject.hasSetter("author");
        if (author){
            this.strictInsertFill(metaObject, "author", String.class, "哈哈");
        }
    }

    //使用mp实现修改操作，这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

}
