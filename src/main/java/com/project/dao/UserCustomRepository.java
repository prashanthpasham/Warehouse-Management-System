package com.project.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface UserCustomRepository {
	public List<Object[]> findUsersList( @Param("code") String code,  @Param("status") String status,
			 @Param("name") String name);
}
