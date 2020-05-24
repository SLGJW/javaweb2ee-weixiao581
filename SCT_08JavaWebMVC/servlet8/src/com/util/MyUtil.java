package com.util;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;

import static java.lang.Integer.parseInt;

public class MyUtil {
    /**
     * 获取数据库连接池对象
     * @return
     */
    public static QueryRunner getDB(){
        DataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        return qr;
    }

    public static int toInt(String str){
        return parseInt(str);
    }

    //通过学号获取学生信息
    public static Student getStudent(int sid) throws Exception{
        QueryRunner qr = MyUtil.getDB();
        String sql = "select * from student where sid=?";
        Student student = qr.query(sql,new BeanHandler<>(Student.class),sid);
        if(student!=null){
            return student;
        }else {
            return null;
        }
    }

    //通过工号获取教师信息
    public static Teacher getTeacher(int tid) throws Exception{
        QueryRunner qr = MyUtil.getDB();
        String sql = "select * from teacher where tid=?";
        Teacher t = qr.query(sql,new BeanHandler<>(Teacher.class),tid);
        String tname = t.getTname();
        String ps = t.getPassword();
        int p = t.getPermissions();
        Teacher teacher = new Teacher(tid,tname,ps,p);
        if(teacher!=null){
            return teacher;
        }else {
            return null;
        }
    }

    //通过课程编号获取课程信息
    public static Course getCourse (int cid) throws Exception{
        QueryRunner qr = MyUtil.getDB();
        String sql = "select * from course where cid=?";
        Course course = qr.query(sql,new BeanHandler<>(Course.class),cid);
        if(course!=null){
            return course;
        }else {
            return null;
        }

    }

    //通过课程编号获取任课教师姓名
    public static String getTeacherName(Course course) throws Exception{
        QueryRunner qr = MyUtil.getDB();
        String sql = "select * from teacher where tid=?";
        Teacher teacher = qr.query(sql, new BeanHandler<>(Teacher.class),course.getCourse_teacher());
        if(teacher!=null){
            return teacher.getTname();
        }else {
            return null;
        }

    }
}
