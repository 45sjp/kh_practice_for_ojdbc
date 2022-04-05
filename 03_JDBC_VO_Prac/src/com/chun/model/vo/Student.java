package com.chun.model.vo;

import java.sql.Date;

public class Student extends Department {
	
	private String studentNo;
	private String studentName;
	private String studentSSN;
	private String studentAddress;
	private Date entranceDate;
	private String absenceYN;
	private String coachProfessorNo;
	
	public Student() {
		super();
	}
	
	public Student(String studentNo, String studentName, String studentSSN, String studentAddress, Date entranceDate,
			String absenceYN, String coachProfessorNo) {
		super();
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.entranceDate = entranceDate;
		this.absenceYN = absenceYN;
		this.coachProfessorNo = coachProfessorNo;
	}

	public Student(String departmentNo, String departmentName, String category, String openYN, int capacity,
			String studentNo, String studentName, String studentSSN, String studentAddress, Date entranceDate,
			String absenceYN, String coachProfessorNo) {
		super(departmentNo, departmentName, category, openYN, capacity);
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentSSN = studentSSN;
		this.studentAddress = studentAddress;
		this.entranceDate = entranceDate;
		this.absenceYN = absenceYN;
		this.coachProfessorNo = coachProfessorNo;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSSN() {
		return studentSSN;
	}

	public void setStudentSSN(String studentSSN) {
		this.studentSSN = studentSSN;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	public String getAbsenceYN() {
		return absenceYN;
	}

	public void setAbsenceYN(String absenceYN) {
		this.absenceYN = absenceYN;
	}

	public String getCoachProfessorNo() {
		return coachProfessorNo;
	}

	public void setCoachProfessorNo(String coachProfessorNo) {
		this.coachProfessorNo = coachProfessorNo;
	}

	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + ", studentName=" + studentName + ", studentSSN=" + studentSSN
				+ ", studentAddress=" + studentAddress + ", entranceDate=" + entranceDate + ", absenceYN=" + absenceYN
				+ ", coachProfessorNo=" + coachProfessorNo + "]";
	}
	
}
