package com.project.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.pojo.BusinessTerritory;
import com.project.pojo.MasterLookUp;
import com.project.service.intf.LoginServiceInf;
@CrossOrigin
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
			JSONObject ob = (JSONObject) parser.parse(data);
			//JSONArray array = (JSONArray) obj.get("hierarchy");
			
			//for (int k = 0; k < array.size(); k++) {
				//JSONObject ob = (JSONObject) array.get(k);
				MasterLookUp lookUp = new MasterLookUp();
				lookUp.setName(ob.get("name").toString());
				lookUp.setType(ob.get("type").toString());
				 if (ob.get("parent")!=null && ob.get("parent").toString().trim().length() > 0)
					lookUp.setParentMasterId(Integer.parseInt(ob.get("parent").toString()));
				 else
					 lookUp.setParentMasterId(0);
				 int count = loginServiceIntf.findMasterLookupParentId(ob.get("type").toString(),lookUp.getParentMasterId());
				  if(count==0){
				    loginServiceIntf.saveMasterLookUp(lookUp);
				    result = "success";
				  }else{
					  result = ob.get("name").toString() +" already exist in parent hierarchy";
				  }

			//}
			
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
	@PostMapping(value = "/save-business-territory", consumes = "application/json")
	public ResponseEntity<String> saveBusinessTerritory(@RequestBody String data) {

		String result = "";
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(data);
			int parentId=Integer.parseInt(obj.get("parentBusinessId").toString());
			String bsName=obj.get("businessName").toString();
			   int count=loginServiceIntf.findBsNameinTerritory(bsName.toLowerCase(), parentId);
			   System.out.println("save");
				if (count == 0) {
					BusinessTerritory territory = new BusinessTerritory();
					territory.setMasterLookUp(loginServiceIntf
							.findByMasterLookupId(Integer.parseInt(obj.get("masterLookupId").toString())));
					territory.setParentId(parentId);
					territory.setBusinessName(bsName);
					System.out.println("save1");
					loginServiceIntf.saveBusinessTerritory(territory);
					result = "success";
				}else {
					result = bsName+" already exist";
				}
			
		} catch (Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}
	@GetMapping(value = "/business-territory", produces = "application/json")
	public JSONArray fetchBusinessTerritory(@RequestParam("type") String type) {
		JSONArray array = new JSONArray();
		try {
			System.out.println("type>>"+type);
			array = loginServiceIntf.fetchBusinessTerritory(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("bt>>"+array.toJSONString());
		return array;

	}
	@GetMapping(value = "/business-territory-data", produces = "application/json")
	public JSONObject fetchBusinessTerritoryById(@RequestParam("id") String id,@RequestParam("type") String type) {
		JSONObject obj = new JSONObject();
		try {
		  obj = loginServiceIntf.getBusinessTerritoryByParentId(Integer.parseInt(id),type);
		  System.out.println("obj>>"+obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;

	}

}
