package com.dao;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.util.MyUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class StudentDaoImpl implements StudentDao {
    private QueryRunner qr = MyUtil.getDB();

    @Override
    public Map<Course,Teacher> findCandT(Student student) throws Exception {
        Map<Course,Teacher> map = new HashMap<>();
        List<Object[]> list = null;
        int sid = student.getSid();
        String sql1 = "select cid from student_course where sid=?";
        list = qr.query(sql1, new ArrayListHandler(),sid);
        for(int i = 0 ; i <list.size() ; i++){
            int cid = parseInt(list.get(i)[0].toString());
            String sql2 = "select * from course where cid=?";
            Course course = qr.query(sql2, new BeanHandler<>(Course.class),cid);
            String sql3 = "select * from teacher where tid=?";
            int tid = course.getCourse_teacher();
            Teacher teacher = qr.query(sql3, new BeanHandler<>(Teacher.class),tid);
            String tname=teacher.getTname();
            String ps=teacher.getPassword();
            int p = teacher.getPermissions();
            Teacher t = new Teacher(tid,tname,ps,p);
            map.put(course, t);
        }
        return map;
    }

    @Override
    public Student doLogin(Student student) throws Exception {
        int sid = student.getSid();
        String password = student.getPassword();
        Student s = null;
        String sql = "select * from student where sid=? and password=?";
        s = qr.query(sql, new BeanHandler<>(Student.class),sid,password);
        return s;
    }

    @Override
    public boolean chooseCourse(int sid,int cid) throws Exception {
        QueryRunner qr = MyUtil.getDB();
        String sql = "insert into student_course values(?,?)";
        int result = qr.update(sql,sid,cid);
        if(result>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean cancelCourse(int sid,int cid) throws Exception {
        QueryRunner qr = MyUtil.getDB();
        String sql = "delete from student_course where sid=? and cid=?";
        int result = qr.update(sql,sid,cid);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Object[]> findNot(int sid) throws Exception {
        QueryRunner qr = MyUtil.getDB();
        System.out.println(sid);
        String sql = "SELECT c.cid,c.cname,t.tname FROM course AS c INNER JOIN teacher AS t ON c.course_teacher = t.tid WHERE tid IN( SELECT course_teacher FROM course WHERE c.cid NOT IN(SELECT cid FROM student_course WHERE sid=?))";
        List<Object[]> list = null;
        list = qr.query(sql, new ArrayListHandler(),sid);
        if(list!=null){
            return list;
        }else{
            return null;
        }
    }
}
