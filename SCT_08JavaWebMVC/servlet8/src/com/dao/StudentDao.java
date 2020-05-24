package com.dao;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    //查询某学生所选的所有课程信息及该课程对应的教师
    public Map<Course,Teacher> findCandT(Student student) throws Exception;
    //验证登录
    public Student doLogin(Student student) throws Exception;
    //选课
    public boolean chooseCourse(int sid,int cid) throws Exception;
    //取消选课
    public boolean cancelCourse(int sid,int cid) throws Exception;
    //查询某学生未选择的所有课程及该课程对应的教师
    public List<Object[]> findNot(int sid) throws Exception;
}
