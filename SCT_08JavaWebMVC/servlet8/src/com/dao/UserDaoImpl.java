package com.dao;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.domain.User;
import com.util.MyUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private QueryRunner qr = MyUtil.getDB();

    @Override
    public User doLogin(User user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        User u = null;
        String sql = "select * from user where username=? and password=?";
        u = qr.query(sql, new BeanHandler<>(User.class),username,password);
        return u;
    }

    @Override
    public boolean insertStudent(Student student) throws Exception {
        String sql ="insert into student(sid,sname,password) values(?,?,?)";
        int result = qr.update(sql, student.getSid(),student.getSname(),student.getPassword());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteStudent(Student student) throws Exception {
        String sql = "delete from student where sid=?";
        int result = qr.update(sql, student.getSid());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) throws Exception {
        String sql = "update student set sname=?,password=? where sid=?";
        int result = qr.update(sql, student.getSname(),student.getPassword(),student.getSid());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Student> selectStudent() throws Exception {
        String sql = "select * from student";
        List<Student> list = null;
        list = qr.query(sql,new BeanListHandler<>(Student.class));
        return list;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) throws Exception {
        String sql ="insert into teacher(tid,tname,password) values(?,?,?)";
        int result = qr.update(sql, teacher.getTid(),teacher.getTname(),teacher.getPassword());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteTeacher(Teacher teacher) throws Exception {
        String sql = "delete from teacher where tid=?";
        int result = qr.update(sql,teacher.getTid());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateTeacher(Teacher teacher) throws Exception {
        String sql = "update teacher set tname=?,password=? where tid=?";
        int result = qr.update(sql, teacher.getTname(),teacher.getPassword(),teacher.getTid());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Teacher> selectTeacher() throws Exception {
        String sql = "select * from teacher";
        List<Teacher> list = new ArrayList<>();
        list = qr.query(sql,new BeanListHandler<>(Teacher.class));
        List<Teacher> teachers = new ArrayList<>();
        int i =1;
        for(Teacher t : list){
            String tname = t.getTname();
            String ps = t.getPassword();
            int p = t.getPermissions();
            Teacher teacher = new Teacher(i,tname,ps,p);
            i++;
            teachers.add(teacher);
        }
        if (teachers != null) {
            return teachers;
        }else {
            return null;
        }
    }

    @Override
    public boolean insertCourse(Course course) throws Exception {
        String sql ="insert into course values(?,?,?)";
        int result = qr.update(sql, course.getCid(),course.getCname(),course.getCourse_teacher());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteCourse(Course course) throws Exception {
        String sql = "delete from course where cid=?";
        int result = qr.update(sql, course.getCid());
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateCourse(Course course,int oldcid) throws Exception {
        String sql = "update course set cid=?,cname=?,course_teacher=? where cid=?";
        int result = qr.update(sql,course.getCid(),course.getCname(),course.getCourse_teacher(),oldcid);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Course> selectCourse(){
        String sql = "select * from course";
        List<Course> list = null;
        try {
            list = qr.query(sql,new BeanListHandler<>(Course.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
