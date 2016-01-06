package com.hpe.ctot.repository;

import org.springframework.data.repository.CrudRepository;

import com.hpe.ctot.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer>, ProjectRepositoryCustom {

}
