package com.ujiuye.bean;

public class DimBean {

	private String sname;
	private String startAge;
	private String endAge;
	public DimBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DimBean(String sname, String startAge, String endAge) {
		super();
		this.sname = sname;
		this.startAge = startAge;
		this.endAge = endAge;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getStartAge() {
		return startAge;
	}
	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}
	public String getEndAge() {
		return endAge;
	}
	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}
	@Override
	public String toString() {
		return "DimBean [sname=" + sname + ", startAge=" + startAge + ", endAge=" + endAge + "]";
	}
	
	
}
