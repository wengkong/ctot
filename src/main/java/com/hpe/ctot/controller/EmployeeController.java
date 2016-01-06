package com.hpe.ctot.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.ctot.model.CompTime;
import com.hpe.ctot.model.Employee;
import com.hpe.ctot.repository.EmployeeRepository;
import com.hpe.ctot.service.UserDetails;

@EnableAutoConfiguration
@RequestMapping("/employees")
@RestController
public class EmployeeController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@RequestMapping(method = GET)
	public Iterable<Employee> findAll() {
		log.info("Find all");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		System.out.println( "username: " + user.getUsername());
		System.out.println( "id: " + user.getId());
		return employeeRepository.findAll();
	}

	@RequestMapping(method = GET, value = "/{id}")
	public Employee findOne(@PathVariable String id) {
		log.info("Find one " + Integer.valueOf(id));
		Employee employee = employeeRepository.findOne(Integer.valueOf(id));
		employeeRepository.isManager(employee);
		return employee;
	}

//	@RequestMapping(method = GET, params = { "email" })
//	public Employee findByEmail(@RequestParam("email") String email) {
//		log.info("Find by email " + email);
//		return employeeRepository.findByEmail(email);
//	}
//
//	@RequestMapping(method = GET, params = { "fullName" })
//	public Employee findByFullName(@RequestParam("fullName") String fullName) {
//		log.info("Find by full name " + fullName);
//		return employeeRepository.findByFullName(fullName);
//	}

	@RequestMapping(method = POST)
	public void save(@RequestBody Employee employee) {
		employeeRepository.save(employee);
	}

	@RequestMapping(method = PATCH, value = "/{id}")
	public Employee update(@PathVariable String id, @RequestBody Employee employee) {
		Employee update = employeeRepository.findOne(Integer.valueOf(id));
		update.setFullName(employee.getFullName());
		update.setManagerEmail(employee.getManagerEmail());
		return employeeRepository.save(update);
	}
	
	@RequestMapping(method = DELETE, value = "/{id}")
	public void delete(@PathVariable String id) {
		employeeRepository.delete(Integer.valueOf(id));
	}

	@RequestMapping(method = GET, value = "/{id}/compTimes")
	public List<CompTime> getCompTimes(@PathVariable("id") String id) {
		log.info("Get comp times " + Integer.valueOf(id));
		// return
		// employeeRepository.findOne(Integer.valueOf(id)).getCompTimes();
		return employeeRepository.getCompTimes(Integer.valueOf(id));
	}

}
