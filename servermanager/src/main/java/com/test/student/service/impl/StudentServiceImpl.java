package com.test.student.service.impl;

import com.test.student.entity.Student;
import com.test.student.mapper.StudentMapper;
import com.test.student.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chaowu
 * @since 2021-10-09
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
