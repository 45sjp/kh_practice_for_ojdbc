package com.kh.model.vo;

import java.sql.Date;

public class Department extends Employee {
	
	private String deptId;
	private String deptTitle;
	private String localId;
	
	public Department() {
		super();
	}

	public Department(String deptId, String deptTitle, String localId) {
		super();
		this.deptId = deptId;
		this.deptTitle = deptTitle;
		this.localId = localId;
	}

	public Department(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, double bonus, String managerId, Date hireDate, Date quitDate,
			String quitYn, String deptId, String deptTitle, String localId) {
		super(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId, hireDate,
				quitDate, quitYn);
		this.deptId = deptId;
		this.deptTitle = deptTitle;
		this.localId = localId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptTitle=" + deptTitle + ", localId=" + localId + "]";
	}

}
