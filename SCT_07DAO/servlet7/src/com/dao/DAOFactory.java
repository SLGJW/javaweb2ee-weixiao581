package com.dao;

public class DAOFactory {
    public static StudentDao getStudentDaoInstance() throws Exception{
        return new StudentDaoImpl();
    }
}
