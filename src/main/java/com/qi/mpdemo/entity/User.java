package com.qi.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //create_time
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //update_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer version;//版本号

    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;  // 0  false  未删除 ；  1  true  已删除
    //private Integer deleted;  或者
}
