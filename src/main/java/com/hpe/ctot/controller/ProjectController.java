package com.hpe.ctot.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.ctot.model.Project;
import com.hpe.ctot.repository.ProjectRepository;

@EnableAutoConfiguration
@RequestMapping("/projects")
@RestController
public class ProjectController {
	private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping(method = GET)
	public Iterable<Project> findAll() {
		log.info("Find all");
		return projectRepository.findAll();
	}
}