package com.hpe.ctot.repository;

import org.springframework.data.repository.CrudRepository;

import com.hpe.ctot.model.CompTime;

public interface CompTimeRepository extends CrudRepository<CompTime, Integer>, CompTimeRepositoryCustom {

}
