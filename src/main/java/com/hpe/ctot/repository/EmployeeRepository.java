package com.hpe.ctot.repository;

import org.springframework.data.repository.CrudRepository;

import com.hpe.ctot.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>, EmployeeRepositoryCustom {

//	@EntityGraph(value = "employeeEntityGraph", type = EntityGraphType.FETCH)
	Employee findByEmail(String email);

//	@EntityGraph(value = "employeeEntityGraph", type = EntityGraphType.FETCH)
	Employee findByFullName(String fullName);
}
