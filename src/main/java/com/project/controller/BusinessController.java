package com.project.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.pojo.MasterLookUp;
import com.project.service.intf.LoginServiceInf;

@RestController
@RequestMapping("/business")
public class BusinessController {
	@Autowired
	private LoginServiceInf loginServiceIntf;

	@PostMapping(value = "/save-hierarchy", consumes = "application/json")
	public ResponseEntity<String> saveHierarchy(@RequestBody String data) {

		String result = "";
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(data);
			JSONArray array = (JSONArray) obj.get("hierarchy");
			MasterLookUp parentLookUp = null;
			for (int k = 0; k < array.size(); k++) {
				JSONObject ob = (JSONObject) array.get(k);
				MasterLookUp lookUp = new MasterLookUp();
				lookUp.setName(ob.get("name").toString());
				lookUp.setType("BT");
				if (parentLookUp == null)
					lookUp.setParentMasterId(0);
				else if (ob.get("parent").toString().trim().length() > 0)
					lookUp.setParentMasterId(parentLookUp.getMasterId());
				parentLookUp = loginServiceIntf.saveMasterLookUp(lookUp);

			}
			result = "success";
		} catch (Exception e) {
			result = "failure";
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/business-hierarchy", produces = "application/json")
	public JSONArray fetchBtHierarchy(@RequestParam("type") String type) {
		JSONArray array = new JSONArray();
		try {
			array = loginServiceIntf.fetchBusinessHierarchy(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;

	}

}
