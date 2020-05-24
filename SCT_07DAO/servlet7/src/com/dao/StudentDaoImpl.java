package com.dao;

import com.domain.Student;
import com.utli.MyUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private QueryRunner qr = MyUtil.getDB();
    @Override
    public boolean doCreate(Student student) throws Exception {
        Object[] obj = {student.getSid(),student.getSname(),student.getSbirthday(),student.getSsex()};
        String sql = "insert into students values(?,?,?,?)";
        int i = qr.update(sql, obj);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean doupdate(Student student) throws Exception {
        Object[] obj = {student.getSname(),student.getSbirthday(),student.getSsex(),student.getSid()};
        String sql = "update students set sname=?,sbirthday=?,ssex=? where sid=?";
        int i = qr.update(sql, obj);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean dodelete(Student student) throws Exception {
        String sid = student.getSid();
        String sql = "delete from students where sid=?";
        int i = qr.update(sql, sid);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Student> findAll() throws Exception {
        List<Student> all = null;
        String sql = "select * from students";
        all = qr.query(sql,new BeanListHandler<Student>(Student.class));
        return all;
    }

    @Override
    public Student findBySid(String sid) throws Exception {
        Student student = null;
        String sql = "select * from students where sid=?";
        student = qr.query(sql,new BeanHandler<>(Student.class),sid);
        return student;
    }

    @Override
    public List<Student> findBySname(String sname) throws Exception {
        List<Student> all = null;
        String sql = "select * from students where sname=?";
        all = qr.query(sql,new BeanListHandler<Student>(Student.class),sname);
        return all;
    }

    @Override
    public List<Student> findBySbirthday(String sbirthday) throws Exception {
        List<Student> all = null;
        String sql = "select * from students where sbirthday=?";
        all = qr.query(sql,new BeanListHandler<Student>(Student.class),sbirthday);
        return all;
    }

    @Override
    public List<Student> findBySsex(String ssex) throws Exception {
        List<Student> all = null;
        String sql = "select * from students where ssex=?";
        all = qr.query(sql,new BeanListHandler<Student>(Student.class),ssex);
        return all;
    }
}
