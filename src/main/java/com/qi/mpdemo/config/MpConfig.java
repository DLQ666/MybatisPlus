package com.qi.mpdemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.qi.mpdemo.mapper")
@Configuration
public class MpConfig {


    //乐观锁插件3.0.5------过时
    /*@Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }*/
    //分页插件3.0.5------过时
    /*@Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }*/

    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    //逻辑删除 插件  3.0.5
    /*@Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }*/

    /**
     * SQL 执行性能分析插件 -------3.0.5
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     *
     * 三种环境
     * dev：开发环境
     * test：测试环境
     * prod：生产环境
     */
    /*@Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(200);//ms，超过此处设置的ms则sql不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }*/
}
