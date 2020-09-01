package com.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
	
	public Users findByUserNameAndPassword(String userName, String password);

	@Query("select count(*) from Users u")
	public int findUsersCount();
}
