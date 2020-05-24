package com.domain;

public class Teacher {
    private int tid;
    private String tname;
    private String password;
    private int permissions;

    public Teacher(int tid, String tname, String password, int permissions) {
        this.tid = tid;
        this.tname = tname;
        this.password = password;
        this.permissions = permissions;
    }

    public Teacher(int tid, String password){
        this.tid = tid;
        this.password = password;
    }

    public Teacher(int tid, String tname, String password) {
        this.tid = tid;
        this.tname = tname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", password='" + password + '\'' +
                ", permissions=" + permissions +
                '}';
    }

    public Teacher() {
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int Tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }
}
