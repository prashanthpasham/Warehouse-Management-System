package com.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
public List<Users> findByUserNameIgnoreCaseOrUserCode(String name,String code);
}
