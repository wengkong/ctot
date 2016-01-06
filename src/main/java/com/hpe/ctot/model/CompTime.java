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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comp_times")
public class CompTime {

	private Integer id;
	private Date compTimeDate;
	private int compTimeHour;
	private Boolean status;
	private String remark;
	private Boolean deleteFlag;
	private Employee employee;

	public CompTime() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comp_time_id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "comp_time_date", nullable = false)
	public Date getCompTimeDate() {
		return compTimeDate;
	}

	public void setCompTimeDate(Date compTimeDate) {
		this.compTimeDate = compTimeDate;
	}

	@Column(name = "comp_time_hour", nullable = false)
	public int getCompTimeHour() {
		return compTimeHour;
	}

	public void setCompTimeHour(int compTimeHour) {
		this.compTimeHour = compTimeHour;
	}

	@Column(name = "approval_status", nullable = false, columnDefinition="char(1)")
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
	@Convert(converter=BooleanToYesNoConverter.class)
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
