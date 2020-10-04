package com.project.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.MasterLookUpRepository;
import com.project.dao.StockLookUpRepository;
import com.project.dao.UomConfigRepository;
import com.project.pojo.MasterLookUp;
import com.project.pojo.StockLookUp;
import com.project.pojo.UOMConfiguration;
import com.project.service.intf.StockServiceIntf;

@Service
@Transactional
public class StockServiceImpl implements StockServiceIntf {
	@Autowired
	private StockLookUpRepository stockLookupRepo;
	@Autowired
	private MasterLookUpRepository masterLookupRepo;
	@Autowired
	private UomConfigRepository uomConfigRepo;

	@Override
	public int findByLookupName(String name, int parentId) {
		// TODO Auto-generated method stub
		return stockLookupRepo.findByLookupName(name, parentId);
	}

	@Override
	public StockLookUp findByStockLookupId(int parentId) {
		// TODO Auto-generated method stub
		return stockLookupRepo.findById(parentId).get();
	}

	@Override
	public String saveStockLookup(StockLookUp stock) {
		// TODO Auto-generated method stub
		stockLookupRepo.save(stock);
		return "success";
	}

	@Override
	public JSONArray fetchStockLookup() {

		JSONArray results = new JSONArray();
		try {
			List<MasterLookUp> ls = masterLookupRepo.fetchBusinessHierarchy("ST");
			if (!ls.isEmpty()) {
				for (MasterLookUp ld : ls) {
					JSONObject obj = new JSONObject();
					JSONArray items = new JSONArray();
					obj.put("name", ld.getName());
					obj.put("id", ld.getMasterId());
					obj.put("parentId", ld.getParentMasterId());
					obj.put("selectedValue", ld.getMasterId() + "@0");
					if (ld.getParentMasterId() > 0) {
						Optional<MasterLookUp> lok = masterLookupRepo.findById(ld.getParentMasterId());
						obj.put("parentName", lok.get().getName());
					} else {
						List<StockLookUp> stock = stockLookupRepo.getStockByLookUpId(ld.getMasterId());
						if (!ls.isEmpty()) {
							JSONObject item1 = new JSONObject();
							item1.put("id", 0);
							item1.put("parentId", 0);
							item1.put("name", "All");
							item1.put("stId", ld.getMasterId() + "@0");
							items.add(item1);
							for (StockLookUp st : stock) {
								JSONObject item = new JSONObject();
								item.put("id", st.getStockLookUpId());
								item.put("parentId", st.getParentId());
								item.put("name", st.getName());
								item.put("stId", ld.getMasterId() + "@" + st.getStockLookUpId());
								items.add(item);
							}
						}
						obj.put("parentName", "");
					}
					obj.put("items", items);
					results.add(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;

	}

	@Override
	public JSONObject fetchStockLookupByParentId(int id) {

		JSONObject ob = new JSONObject();
		JSONArray results = new JSONArray();
		try {
			List<StockLookUp> ls = stockLookupRepo.getStockByLookUpByparentId(id);
			int lookUpId = 0;
			if (!ls.isEmpty()) {
				for (StockLookUp bt : ls) {
					if (results.size() == 0) {
						JSONObject item1 = new JSONObject();
						item1.put("id", 0);
						item1.put("parentId", 0);
						item1.put("name", "All");
						item1.put("bsid", bt.getMasterLookUp().getMasterId() + "@0");
						results.add(item1);
					}
					JSONObject obj = new JSONObject();
					obj.put("id", bt.getStockLookUpId());
					obj.put("parentId", bt.getParentId());
					obj.put("name", bt.getName());
					obj.put("bsid", bt.getMasterLookUp().getMasterId() + "@" + bt.getStockLookUpId());
					if (lookUpId == 0) {
						lookUpId = bt.getMasterLookUp().getMasterId();
					}
					results.add(obj);
				}
			}
			ob.put("items", results);
			ob.put("masterLookupId", lookUpId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ob;

	}

	@Override
	public int findByUomName(String name) {
		return uomConfigRepo.findByUomName(name);
	}

	@Override
	public UOMConfiguration findUomConfigById(int id) {
		// TODO Auto-generated method stub
		return uomConfigRepo.findById(id).get();
	}

	@Override
	public String saveUomConfiguration(UOMConfiguration config) {
		UOMConfiguration uom=uomConfigRepo.save(config);
		uom.setChildUomIds(config.getChildUomIds()!=null?(config.getChildUomIds()+uom.getUomConfigId()+"@"):uom.getUomConfigId()+"@");
		return "success";
	}

}
