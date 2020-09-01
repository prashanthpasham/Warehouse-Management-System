package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.UploadFileHistory;

@Repository
public interface UploadFileHistoryRepository extends CrudRepository<UploadFileHistory, Integer> {
	@Query("from UploadFileHistory a where a.status=:Status")
	public List<UploadFileHistory> uploadFiles(@Param("Status") String status);
}
