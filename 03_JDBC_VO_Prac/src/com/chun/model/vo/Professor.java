package com.chun.model.vo;

import java.sql.Date;

public class Professor extends ClassProfessor {
	
	private String professorName;
	private String professorSSN;
	private String professorAddress;
	
	public Professor() {
		super();
	}
	
	public Professor(String professorName, String professorSSN, String professorAddress) {
		super();
		this.professorName = professorName;
		this.professorSSN = professorSSN;
		this.professorAddress = professorAddress;
	}

	public Professor(String departmentNo, String departmentName, String category, String openYN, int capacity,
			String studentNo, String studentName, String studentSSN, String studentAddress, Date entranceDate,
			String absenceYN, String coachProfessorNo, String termNo, String classNo, double point,
			String preattendingClassNo, String className, String classType, String professorNo, String professorName,
			String professorSSN, String professorAddress) {
		super(departmentNo, departmentName, category, openYN, capacity, studentNo, studentName, studentSSN,
				studentAddress, entranceDate, absenceYN, coachProfessorNo, termNo, classNo, point, preattendingClassNo,
				className, classType, professorNo);
		this.professorName = professorName;
		this.professorSSN = professorSSN;
		this.professorAddress = professorAddress;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getProfessorSSN() {
		return professorSSN;
	}

	public void setProfessorSSN(String professorSSN) {
		this.professorSSN = professorSSN;
	}

	public String getProfessorAddress() {
		return professorAddress;
	}

	public void setProfessorAddress(String professorAddress) {
		this.professorAddress = professorAddress;
	}

	@Override
	public String toString() {
		return "Professor [professorName=" + professorName + ", professorSSN=" + professorSSN + ", professorAddress="
				+ professorAddress + "]";
	}
	
}
