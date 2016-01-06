package com.hpe.ctot.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.ctot.model.CompTime;
import com.hpe.ctot.repository.CompTimeRepository;
import com.hpe.ctot.repository.TaskRepository;

@RestController
@RequestMapping("/comptimes")
public class CompTimeController {
	private static final Logger log = LoggerFactory.getLogger(CompTimeController.class);

	@Autowired
	CompTimeRepository comptimeRepository;

	@RequestMapping(method = GET)
	public Iterable<CompTime> findAll() {
		log.info("Find all");
		return comptimeRepository.findAll();
	}

	@RequestMapping(method = POST)
	public CompTime createCompTime(@RequestBody CompTime compTime) {
		compTime.setDeleteFlag(Boolean.FALSE);
		compTime.setStatus(Boolean.FALSE);
		compTime.setRemark("-");
		return comptimeRepository.save(compTime);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public void deleteCompTime(@PathVariable String id) {
		comptimeRepository.delete((Integer.valueOf(id)));

	}

	@RequestMapping(value = "/{id}", method = PUT)
	public CompTime editCompTime(@PathVariable String id, @RequestBody CompTime compTime) {
		CompTime editCompTime = comptimeRepository.findOne(Integer.valueOf(id));
		editCompTime.setCompTimeDate(compTime.getCompTimeDate());
		editCompTime.setCompTimeHour(compTime.getCompTimeHour());
		return comptimeRepository.save(editCompTime);
	}

}
