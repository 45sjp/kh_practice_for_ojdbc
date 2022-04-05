package com.chun.model.vo;

import java.sql.Date;

public class Grade extends Student {

	private String termNo;
	private String classNo;
	private double point;
	
	public Grade() {
		super();
	}

	public Grade(String termNo, String classNo, double point) {
		super();
		this.termNo = termNo;
		this.classNo = classNo;
		this.point = point;
	}

	public Grade(String departmentNo, String departmentName, String category, String openYN, int capacity,
			String studentNo, String studentName, String studentSSN, String studentAddress, Date entranceDate,
			String absenceYN, String coachProfessorNo, String termNo, String classNo, double point) {
		super(departmentNo, departmentName, category, openYN, capacity, studentNo, studentName, studentSSN,
				studentAddress, entranceDate, absenceYN, coachProfessorNo);
		this.termNo = termNo;
		this.classNo = classNo;
		this.point = point;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Grade [termNo=" + termNo + ", classNo=" + classNo + ", point=" + point + "]";
	}
	
}
