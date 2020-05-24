package com.dao;

import com.domain.Student;

import java.util.List;

public interface StudentDao {
    public boolean doCreate(Student student) throws Exception;
    public boolean doupdate(Student student) throws Exception;
    public boolean dodelete(Student student) throws Exception;
    public List<Student> findAll() throws Exception;
    public Student findBySid(String sid) throws Exception;
    public List<Student> findBySname(String sname) throws Exception;
    public List<Student> findBySbirthday(String sbirthday) throws Exception;
    public List<Student> findBySsex(String ssex) throws Exception;
}
