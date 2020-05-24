package com.domain;

import java.util.Date;

public class Student {
    private String sid;
    private String sname;
    private Date sbirthday;
    private String ssex;

    public Student() {
    }

    public Student(String sid, String sname, Date sbirthday, String ssex) {
        this.sid = sid;
        this.sname = sname;
        this.sbirthday = sbirthday;
        this.ssex = ssex;
    }

    @Override
    public String toString() {
        return "student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", sbirthday=" + sbirthday +
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

    public Date getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}
