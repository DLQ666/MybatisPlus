package com.qi.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 *@program: mpdemo
 *@description:
 *@author: Hasee
 *@create: 2021-04-01 19:53
 */
@Data
public class Product {

    private Long id;
    private String name;
    private Integer price;

    @Version
    private Integer version;
}
