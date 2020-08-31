package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.ScheduleJobs;

@Repository
public interface ScheduleJobsRepository extends CrudRepository<ScheduleJobs, Integer> {
	@Query("from ScheduleJobs s where s.status=:status")
	public List<ScheduleJobs> fetchScheduleJobs(@Param("status") String status);
}
