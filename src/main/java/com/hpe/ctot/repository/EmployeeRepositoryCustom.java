package com.hpe.ctot.repository;

import java.util.List;

import com.hpe.ctot.model.CompTime;
import com.hpe.ctot.model.Employee;

public interface EmployeeRepositoryCustom {
	public boolean isManager(Employee employee);
	public List<CompTime> getCompTimes(Integer employeeId);
	public Employee findOne(Integer employeeId);
}
