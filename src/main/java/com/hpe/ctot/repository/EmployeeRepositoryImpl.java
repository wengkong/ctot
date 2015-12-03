package com.hpe.ctot.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.hpe.ctot.model.CompTime;
import com.hpe.ctot.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

	@Autowired
    private EntityManager entityManager;
	
	@Override
	public Employee findOne(Integer employeeId) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		employee.setManager(isManager(employee));
		return employee;
	}

	@Override
	public boolean isManager(Employee employee) {
		Query query = entityManager.createQuery("select count(e.managerEmail) from Employee as e where e.managerEmail = :email");
		query.setParameter("email", employee.getEmail());
		return ((Long) query.getSingleResult()).intValue() > 0;
	}
	
	@Override
	public List<CompTime> getCompTimes(Integer employeeId) {
		TypedQuery<CompTime> query = entityManager.createQuery("SELECT c FROM CompTime AS c WHERE c.employee.id = :employeeId", CompTime.class);
		query.setParameter("employeeId", employeeId);
		return query.getResultList();
	}	
}
