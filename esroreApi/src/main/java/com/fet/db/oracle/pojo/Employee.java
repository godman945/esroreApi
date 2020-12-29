package com.fet.db.oracle.pojo;
// Generated 2020/12/25 �U�� 02:18:50 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	private String seq;
	private String employeeNo;
	private String password;
	private String name;
	private Date pwdChgDate;
	private String tel;
	private String email;
	private Character lockstatus;
	private char activation;
	private String parentEmployeeNo;
	private Character title;
	private String department;
	private String ntAccount;

	public Employee() {
	}

	public Employee(String seq, String name, char activation) {
		this.seq = seq;
		this.name = name;
		this.activation = activation;
	}

	public Employee(String seq, String employeeNo, String password, String name, Date pwdChgDate, String tel,
			String email, Character lockstatus, char activation, String parentEmployeeNo, Character title,
			String department, String ntAccount) {
		this.seq = seq;
		this.employeeNo = employeeNo;
		this.password = password;
		this.name = name;
		this.pwdChgDate = pwdChgDate;
		this.tel = tel;
		this.email = email;
		this.lockstatus = lockstatus;
		this.activation = activation;
		this.parentEmployeeNo = parentEmployeeNo;
		this.title = title;
		this.department = department;
		this.ntAccount = ntAccount;
	}

	@Id

	@Column(name = "SEQ", unique = true, nullable = false, length = 32)
	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	@Column(name = "EMPLOYEE_NO", length = 32)
	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	@Column(name = "PASSWORD", length = 12)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PWD_CHG_DATE", length = 7)
	public Date getPwdChgDate() {
		return this.pwdChgDate;
	}

	public void setPwdChgDate(Date pwdChgDate) {
		this.pwdChgDate = pwdChgDate;
	}

	@Column(name = "TEL", length = 10)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "LOCKSTATUS", length = 1)
	public Character getLockstatus() {
		return this.lockstatus;
	}

	public void setLockstatus(Character lockstatus) {
		this.lockstatus = lockstatus;
	}

	@Column(name = "ACTIVATION", nullable = false, length = 1)
	public char getActivation() {
		return this.activation;
	}

	public void setActivation(char activation) {
		this.activation = activation;
	}

	@Column(name = "PARENT_EMPLOYEE_NO", length = 32)
	public String getParentEmployeeNo() {
		return this.parentEmployeeNo;
	}

	public void setParentEmployeeNo(String parentEmployeeNo) {
		this.parentEmployeeNo = parentEmployeeNo;
	}

	@Column(name = "TITLE", length = 1)
	public Character getTitle() {
		return this.title;
	}

	public void setTitle(Character title) {
		this.title = title;
	}

	@Column(name = "DEPARTMENT", length = 60)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "NT_ACCOUNT", length = 30)
	public String getNtAccount() {
		return this.ntAccount;
	}

	public void setNtAccount(String ntAccount) {
		this.ntAccount = ntAccount;
	}

}