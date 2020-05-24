package com.dao;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.util.MyUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherDaoImpl implements TeacherDao {
    QueryRunner qr = MyUtil.getDB();

    @Override
    public Teacher doLogin(Teacher teacher) throws Exception{
        int tid = teacher.getTid();
        String password = teacher.getPassword();
        String sql = "select * from teacher where tid=? and password=?";
        Teacher t = qr.query(sql, new BeanHandler<>(Teacher.class),tid,password);
        String tname = t.getTname();
        String ps = t.getPassword();
        int p = t.getPermissions();
        Teacher tea = new Teacher(tid,tname,ps,p);
        return tea;
    }
    @Override
    public List<Course> findTeachCourses(Teacher teacher) throws Exception {
        List<Course> courses = new ArrayList<>();
        int tid = teacher.getTid();
        String sql = "select * from course where course_teacher=?";
        courses = qr.query(sql, new BeanListHandler<>(Course.class),tid);
        return courses;
    }

    @Override
    public Map<Course,List<Student>> findTeachStudents(Teacher teacher) throws Exception {
        Map<Course,List<Student>> map = new HashMap<>();
        List<Course> courses = findTeachCourses(teacher);
        List<Object[]> list = new ArrayList<>();
        String sql1 = "select sid from student_course where cid=?";
        for(Course course : courses){
            List<Student> students = new ArrayList<>();
            System.out.println("course.getCid()"+course.getCid());
            list = qr.query(sql1, new ArrayListHandler(),course.getCid());
            String sql2 = "select s.* from student s,student_course s_c where s_c.cid=? and s.sid=s_c.sid";
            for(Object[] objs : list){
                System.out.println("obj[0]"+objs[0]);
                int cid = MyUtil.toInt(objs[0].toString());
                Student student = qr.query(sql2, new BeanHandler<>(Student.class),cid);
                students.add(student);
            }
            map.put(course,students);
        }
//        for(Course course : courses){
//            list = qr.query(sql1, new ArrayListHandler(),course.getCid());
//            String sql2 = "select * form student where sid=?";
//            for(Object[] objs : list){
//                List<Object[]> list2 = null;
//                Student stu = qr.query(sql2, new BeanHandler<>(Student.class),objs[0]);
//                String sql3 = "select cid from student_course where sid=?";
//                list2 = qr.query(sql3, new ArrayListHandler(),stu.getSid());
//                for(Object[] obj : list2){
//                    String sql4 = "select * from course where cid=?";
//                    List<Course> coursess = qr.query(sql4,new BeanListHandler<>(Course.class),obj[0]);
//                    map.put(stu, coursess);
//                }
//            }
//        }
        return map;
    }
}
