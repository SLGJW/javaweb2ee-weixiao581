package com.bean;

import java.util.Date;

public class Student {
	
	private int sid;// 主键
	private String sname; //姓名
	private int age; //年龄
	private int sex; //性别
	private String sclass; //班级
	private String shobby; // 爱好
	private String edu; //学历
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(String sname, int age, int sex, String sclass, String shobby, String edu) {
		super();
		this.sname = sname;
		this.age = age;
		this.sex = sex;
		this.sclass = sclass;
		this.shobby = shobby;
		this.edu = edu;
	}

	public Student(int sid, String sname, int age, int sex, String sclass, String shobby, String edu) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.age = age;
		this.sex = sex;
		this.sclass = sclass;
		this.shobby = shobby;
		this.edu = edu;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public String getShobby() {
		return shobby;
	}
	public void setShobby(String shobby) {
		this.shobby = shobby;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", age=" + age + ", sex=" + sex + ", sclass=" + sclass
				+ ", shobby=" + shobby + ", edu=" + edu + "]";
	}	
	
}
