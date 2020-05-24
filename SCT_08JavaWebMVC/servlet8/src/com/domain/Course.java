package com.domain;

public class Course {
    private int cid;
    private String cname;
    private int course_teacher;

    public Course() {
    }

    public Course(int cid, String cname, int course_teacher) {
        this.cid = cid;
        this.cname = cname;
        this.course_teacher = course_teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", course_teacher=" + course_teacher +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCourse_teacher() {
        return course_teacher;
    }

    public void setCourse_teacher(int course_teacher) {
        this.course_teacher = course_teacher;
    }
}
