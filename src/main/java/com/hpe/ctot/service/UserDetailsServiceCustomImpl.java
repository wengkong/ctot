package com.hpe.ctot.service;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hpe.ctot.model.Employee;
import com.hpe.ctot.repository.EmployeeRepository;

@Service("userDetailsService")
public class UserDetailsServiceCustomImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceCustomImpl.class);
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UserDetailsServiceCustomImpl.loadUserByUsername " + username);
		
		// check whether employee exist
		Employee employee = employeeRepository.findByEmail(username);
		
		// create employee entry if employee not exist
		if (employee == null) {
			employee = new Employee();
			employee.setEmail(username);
			employee.setFullName("");			
			employeeRepository.save(employee);
		}
		
		// Employee.isManager should be use to determine the role of an employee.
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("MANAGER"));
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		/* 
		 * Since authentication is to be done using OneHP in the future,
		 * we are not storing any password.
		 * A dummy password set here just to satisfy how Spring Security works.
		 */
		
		//PasswordEncoder encoder = new BCryptPasswordEncoder();
		return new User(employee.getEmail(), "password", authorities);
	}

}
