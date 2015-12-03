package com.hpe.ctot.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	private Integer id;
	private String taskDescription;
	private Date workedDate;
	private Integer workedHour;
	private Boolean status;
	private String remark;
	private Boolean deleteFlag;
	private Employee employee;
	private Project project;

	public Task() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id", nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "task_desc", nullable = false, length = 100)
	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	@Column(name = "worked_date", nullable = false)
	public Date getWorkedDate() {
		return workedDate;
	}

	public void setWorkedDate(Date workedDate) {
		this.workedDate = workedDate;
	}

	@Column(name = "worked_hour", nullable = false)
	public Integer getWorkedHour() {
		return workedHour;
	}

	public void setWorkedHour(Integer workedHour) {
		this.workedHour = workedHour;
	}

	//@Column(name = "rq_status", nullable = false, columnDefinition="char(1) default 'N'")
	@Column(name = "rq_status", nullable = false, columnDefinition="char(1)")
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "remark", nullable = false, length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "delete_flag", nullable = false, columnDefinition="char(1)")
	@Convert(converter = BooleanToYesNoConverter.class)
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "employee_id", nullable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "project_id", nullable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
