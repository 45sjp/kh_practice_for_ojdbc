package com.chun.model.vo;

import java.sql.Date;

public class Class extends Grade {
	
	private String preattendingClassNo;
	private String className;
	private String classType;
	
	public Class() {
		super();
	}

	public Class(String preattendingClassNo, String className, String classType) {
		super();
		this.preattendingClassNo = preattendingClassNo;
		this.className = className;
		this.classType = classType;
	}

	public Class(String departmentNo, String departmentName, String category, String openYN, int capacity,
			String studentNo, String studentName, String studentSSN, String studentAddress, Date entranceDate,
			String absenceYN, String coachProfessorNo, String termNo, String classNo, double point,
			String preattendingClassNo, String className, String classType) {
		super(departmentNo, departmentName, category, openYN, capacity, studentNo, studentName, studentSSN,
				studentAddress, entranceDate, absenceYN, coachProfessorNo, termNo, classNo, point);
		this.preattendingClassNo = preattendingClassNo;
		this.className = className;
		this.classType = classType;
	}

	public String getPreattendingClassNo() {
		return preattendingClassNo;
	}

	public void setPreattendingClassNo(String preattendingClassNo) {
		this.preattendingClassNo = preattendingClassNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Override
	public String toString() {
		return "Class [preattendingClassNo=" + preattendingClassNo + ", className=" + className + ", classType="
				+ classType + "]";
	}

}
