package com.chun.model.vo;

import java.sql.Date;

public class ClassProfessor extends Class {
	
	private String professorNo;
	
	public ClassProfessor() {
		super();
	}

	public ClassProfessor(String professorNo) {
		super();
		this.professorNo = professorNo;
	}

	public ClassProfessor(String departmentNo, String departmentName, String category, String openYN, int capacity,
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
