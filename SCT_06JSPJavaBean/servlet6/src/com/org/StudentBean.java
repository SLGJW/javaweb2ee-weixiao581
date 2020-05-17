package com.org;

import java.sql.Date;

public class StudentBean {
    private String sid;
    private String sname;
    private String ssex;

    public StudentBean(){}
    public StudentBean(String sid, String sname, String ssex){
        this.sid=sid;
        this.sname=sname;
        this.ssex=ssex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                '}';
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}
