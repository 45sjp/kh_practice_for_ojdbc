package com.chun.model.vo;

import java.sql.Date;

public class Class_Professor extends Class {
	
	private String professorNo;
	
	public Class_Professor() {
		super();
	}

	public Class_Professor(String professorNo) {
		super();
		this.professorNo = professorNo;
	}

	public Class_Professor(String departmentNo, String departmentName, String category, String openYN, int capacity,
			String studentNo, String studentName, String studentSSN, String studentAddress, Date entranceDate,
			String absenceYN, String coachProfessorNo, String termNo, String classNo, double point,
			String preattendingClassNo, String className, String classType, String professorNo) {
		super(departmentNo, departmentName, category, openYN, capacity, studentNo, studentName, studentSSN,
				studentAddress, entranceDate, absenceYN, coachProfessorNo, termNo, classNo, point, preattendingClassNo,
				className, classType);
		this.professorNo = professorNo;
	}

	public String getProfessorNo() {
		return professorNo;
	}

	public void setProfessorNo(String professorNo) {
		this.professorNo = professorNo;
	}

	@Override
	public String toString() {
		return "Class_Professor [professorNo=" + professorNo + "]";
	}
	
}
