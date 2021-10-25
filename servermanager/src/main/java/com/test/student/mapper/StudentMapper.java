package com.test.student.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.student.entity.Student;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chaowu
 * @since 2021-10-09
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
