package com.hpe.ctot.repository;

import org.springframework.data.repository.CrudRepository;

import com.hpe.ctot.model.Task;
	
public interface TaskRepository extends CrudRepository< Task, Integer>, TaskRepositoryCustom{ 
			
}
