package com.hpe.ctot.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employees")
// @NamedEntityGraph(name = "employeeEntityGraph", attributeNodes = {
// @NamedAttributeNode("email"),
// @NamedAttributeNode("fullName"),
// @NamedAttributeNode("managerEmail"),
// @NamedAttributeNode("sumOfWorkedHour"),
// @NamedAttributeNode("sumOfCompTimeTakenHour") })
public class Employee {

	private Integer id;
	private String email;
	private String fullName;
	private String managerEmail;
	private int sumOfWorkedHour;
	private int sumOfCompTimeTakenHour;
	private List<Task> tasks;
	private List<CompTime> compTimes;
	private List<Project> projects;
	private boolean isManager;

	public Employee() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "full_name", nullable = false, length = 255)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "manager_email", nullable = true, length = 100)
	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	@Column(name = "sum_worked_hour", nullable = false)
	public int getSumOfWorkedHour() {
		return sumOfWorkedHour;
	}

	public void setSumOfWorkedHour(int sumOfWorkedHour) {
		this.sumOfWorkedHour = sumOfWorkedHour;
	}

	@Column(name = "sum_comp_time_taken_hour", nullable = false)
	public int getSumOfCompTimeTakenHour() {
		return sumOfCompTimeTakenHour;
	}

	public void setSumOfCompTimeTakenHour(int sumOfCompTimeTakenHour) {
		this.sumOfCompTimeTakenHour = sumOfCompTimeTakenHour;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	public List<CompTime> getCompTimes() {
		return compTimes;
	}

	public void setCompTimes(List<CompTime> compTimes) {
		this.compTimes = compTimes;
	}

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "employees_projects", joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employee_id") , inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "project_id") )
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Transient
	public boolean isManager() {
		return isManager;
	}
}
