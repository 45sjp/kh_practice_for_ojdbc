package com.kh.model.vo;

import java.sql.Date;

public class Job extends Employee {
	
	private String jobName;
	
	public Job() {
		super();
	}

	public Job(String jobName) {
		super();
		this.jobName = jobName;
	}

	public Job(String empId, String empName, String empNo, String email, String phone, String deptCode, String jobCode,
			String salLevel, int salary, double bonus, String managerId, Date hireDate, Date quitDate, String quitYn,
			String jobName) {
		super(empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary, bonus, managerId, hireDate,
				quitDate, quitYn);
		this.jobName = jobName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public String toString() {
		return "Job [jobName=" + jobName + "]";
	}
	
}
