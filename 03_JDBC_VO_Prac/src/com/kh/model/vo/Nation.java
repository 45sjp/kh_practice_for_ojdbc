package com.kh.model.vo;

import java.sql.Date;

public class Nation extends Location {
	
	private String nationalName;
	
	public Nation() {
		super();
	}

	public Nation(String nationalName) {
		super();
		this.nationalName = nationalName;
	}

	public Nation(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, double bonus, String managerId, Date hireDate, Date quitDate,
			String quitYn, String deptId, String deptTitle, String localId, String localCode, String nationalCode,
			String localName, String nationalName) {
		super(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId, hireDate,
				quitDate, quitYn, deptId, deptTitle, localId, localCode, nationalCode, localName);
		this.nationalName = nationalName;
	}

	public String getNationalName() {
		return nationalName;
	}

	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}

	@Override
	public String toString() {
		return "Nation [nationalName=" + nationalName + "]";
	}
	
}
