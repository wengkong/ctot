package com.hpe.ctot.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.ctot.model.Employee;
import com.hpe.ctot.repository.EmployeeRepository;
import com.hpe.ctot.service.UserDetails;

@EnableAutoConfiguration
@RequestMapping("/profile")
@RestController
public class ProfileController {
	private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping(method = GET)
	public Employee findOne() {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		log.info("Find one " + user.getId());
		Employee employee = employeeRepository.findOne(user.getId());
		//employeeRepository.isManager(employee);
		return employee;
	}
	
	@RequestMapping(method = POST)
	public Employee save(@RequestBody Employee employee) {
		log.info("Save " + employee.getId());
		return employeeRepository.save(employee);
	}
}
