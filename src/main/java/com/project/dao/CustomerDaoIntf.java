package com.project.dao;

import java.util.List;

import org.json.simple.JSONObject;

import com.project.dto.CustomerDto;

public interface CustomerDaoIntf {
	public List<CustomerDto> customersList(JSONObject filters);
}
