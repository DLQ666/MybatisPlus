package com.qi.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qi.mpdemo.entity.eduCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author dlq
 * @since 2020-06-18
 */
@Mapper
@Component
public interface EduCourseMapper extends BaseMapper<eduCourse> {

}
