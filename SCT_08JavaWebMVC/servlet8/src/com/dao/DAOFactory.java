package com.dao;

public class DAOFactory {
    public static StudentDao getStudentDaoInstance() throws Exception{
        return new StudentDaoImpl();
    }
    public static TeacherDao getTeacherDaoInstance() throws Exception{
        return new TeacherDaoImpl();
    }
    public static UserDao getUserDaoInstance() throws Exception{
        return new UserDaoImpl();
    }
}
