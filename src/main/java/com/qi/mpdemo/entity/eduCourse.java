package com.qi.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author dlq
 * @since 2020-06-18
 */
@Data
public class eduCourse {

    public static final String COURSE_DRAFT = "Draft";//未发布
    public static final String COURSE_NORMAL = "Normal";//已发布


    private String id;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;


}
