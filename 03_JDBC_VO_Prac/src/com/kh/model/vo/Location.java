package com.kh.model.vo;

import java.sql.Date;

public class Location extends Department {
	
	private String localCode;
	private String nationalCode;
	private String localName;
	
	public Location() {
		super();
	}

	public Location(String localCode, String nationalCode, String localName) {
		super();
		this.localCode = localCode;
		this.nationalCode = nationalCode;
		this.localName = localName;
	}

	public Location(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, double bonus, String managerId, Date hireDate, Date quitDate,
			String quitYn, String deptId, String deptTitle, String localId, String localCode, String nationalCode,
			String localName) {
		super(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId, hireDate,
				quitDate, quitYn, deptId, deptTitle, localId);
		this.localCode = localCode;
		this.nationalCode = nationalCode;
		this.localName = localName;
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Override
	public String toString() {
		return "Location [localCode=" + localCode + ", nationalCode=" + nationalCode + ", localName=" + localName + "]";
	}
	
}
