package com.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.UsersDto;

@RestController
@RequestMapping(value="/login")
public class UserController {
	@PostMapping(value="/validate")
	public String loginValidation(UsersDto users) {
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;

	}
}
