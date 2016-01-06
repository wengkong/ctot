package com.hpe.ctot.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.ctot.model.CompTime;
import com.hpe.ctot.model.Task;
import com.hpe.ctot.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskRepository taskRepository;

	@RequestMapping(method = GET)
	public Iterable<Task> findAll() {
		log.info("Find all");
		return taskRepository.findAll();
	}

	@RequestMapping(method = POST)
	public Task createTask(@RequestBody Task task) {
		task.setDeleteFlag(Boolean.FALSE);
		task.setStatus(Boolean.FALSE);
		task.setRemark("-");
		return taskRepository.save(task);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public void deleteTask(@PathVariable String id) {
		taskRepository.delete((Integer.valueOf(id)));

	}

	@RequestMapping(value = "/{id}", method = PUT)
	public Task editTask(@PathVariable String id, @RequestBody Task task) {
		Task editTask = taskRepository.findOne(Integer.valueOf(id));
		editTask.setTaskDescription(task.getTaskDescription());
		editTask.setWorkedDate(task.getWorkedDate());
		editTask.setWorkedHour(task.getWorkedHour());
		return taskRepository.save(editTask);
	}
	
	@RequestMapping(value = "/approval/{id}", method = PUT)
	public Task approveTask(@PathVariable String id, @RequestBody Task task) {
		Task editTask = taskRepository.findOne(Integer.valueOf(id));
		editTask.setStatus(task.getStatus());
		return taskRepository.save(editTask);
	}
	

}
