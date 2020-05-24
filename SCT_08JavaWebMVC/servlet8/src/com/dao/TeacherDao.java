package com.dao;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherDao {
    //登录检验
    public Teacher doLogin(Teacher teacher) throws Exception;
    //查询某教师所教的所有课程
    public List<Course> findTeachCourses(Teacher teacher) throws Exception;
    //查询某教师所教的所有课程以及选择该课程的学生
    public Map<Course,List<Student>> findTeachStudents(Teacher teacher) throws Exception;
}
