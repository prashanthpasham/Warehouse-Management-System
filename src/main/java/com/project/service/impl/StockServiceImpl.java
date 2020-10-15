package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.MasterLookUpRepository;
import com.project.dao.StockLookUpRepository;
import com.project.dao.StockRepository;
import com.project.dao.UomConfigRepository;
import com.project.dto.StockDto;
import com.project.pojo.MasterLookUp;
import com.project.pojo.Stock;
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
	@Autowired
	private StockRepository stockRepo;

	@Override
	public int findByLookupName(String name, int parentId) {
		// TODO Auto-generated method stub
		return stockLookupRepo.findByLookupName(name, parentId);
	}

	@Override
	public StockLookUp findByStockLookupId(int parentId) {
		// TODO Auto-generated method stub
		Optional<StockLookUp> opt = stockLookupRepo.findById(parentId);
		if (opt.isPresent())
			return opt.get();
		else
			return null;
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
		Optional<UOMConfiguration> opt = uomConfigRepo.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

	@Override
	public String saveUomConfiguration(UOMConfiguration config) {
		UOMConfiguration uom = uomConfigRepo.save(config);
		uom.setChildUomIds(config.getChildUomIds() != null ? (config.getChildUomIds() + uom.getUomConfigId() + "@")
				: uom.getUomConfigId() + "@");
		return "success";
	}

	@Override
	public String createStock(StockDto dto) {
		try {
			List<Stock> stockList = stockRepo.findBySkuCodeIgnoreCase(dto.getSkuCode().toLowerCase());
			if (stockList != null && !stockList.isEmpty()) {
				return "SkuCode:" + dto.getSkuCode() + " already exist";
			} else {
				Stock stock = new Stock();
				stock.setSkuCode(dto.getSkuCode());
				stock.setSkuDescription(dto.getSkuDescription());
				stock.setStatus(dto.getStatus());
				stock.setCreatedDate(new Date());
				stock.setManagedBy(dto.getManagedBy());
				stock.setPrice(dto.getPrice());
				stock.setUnitPrice(dto.getUnitPrice());
				stock.setDefaultPackSize(dto.getDefaultPackSize());
				stock.setDefaultPackQty(dto.getDefaultPackQty());
				stock.setStockLookup(stockLookupRepo.findById(dto.getStockLookupId()).get());
				stock.setUomConfigId(uomConfigRepo.findById(dto.getUomConfigId()).get());
				/*
				 * if (dto.getImages() != null && !dto.getImages().isEmpty()) { List<Images>
				 * imgList = new ArrayList<Images>(); for (String img : dto.getImages()) {
				 * Images image = new Images();
				 * 
				 * imgList.add(image); } stock.setImages(imgList); }
				 */
				stockRepo.save(stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@Override
	public JSONObject stockList(int id) {
		JSONObject obj = new JSONObject();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Optional<Stock> opt = stockRepo.findById(id);
			if (opt.isPresent()) {
				Stock stock = opt.get();
				obj.put("stockId", stock.getStockId());
				obj.put("skuCode", stock.getSkuCode());
				obj.put("skuDescription", stock.getSkuDescription());
				obj.put("status", stock.getStatus());
				obj.put("managedBy", stock.getManagedBy());
				obj.put("createdDate", sdf.format(stock.getCreatedDate()));
				obj.put("price", stock.getPrice());
				obj.put("unitPrice", stock.getUnitPrice());
				obj.put("uomConfigId", stock.getUomConfigId().getUomConfigId());
				obj.put("defaultPackSize", stock.getDefaultPackSize());
				obj.put("defaultPackQty", stock.getDefaultPackQty());
				obj.put("stockLookupId", stock.getStockLookup().getStockLookUpId());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public JSONArray stockList() {
		JSONArray results = new JSONArray();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Iterable<Stock> it = stockRepo.findAll();
			it.forEach(new Consumer<Stock>() {
				@Override
				public void accept(Stock stock) {

					JSONObject obj = new JSONObject();
					obj.put("stockId", stock.getStockId());
					obj.put("skuCode", stock.getSkuCode());
					obj.put("skuDescription", stock.getSkuDescription());
					obj.put("status", stock.getStatus());
					obj.put("managedBy", stock.getManagedBy());
					obj.put("createdDate", sdf.format(stock.getCreatedDate()));
					obj.put("modifiedDate", stock.getModifiedDate() != null ? sdf.format(stock.getModifiedDate()) : "");
					results.add(obj);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public JSONArray uomConfigurationList() {
		JSONArray results = new JSONArray();
		try {
			Iterable<UOMConfiguration> it = uomConfigRepo.findAll();
			it.forEach(new Consumer<UOMConfiguration>() {
				@Override
				public void accept(UOMConfiguration uom) {
					JSONObject obj = new JSONObject();
					obj.put("id", uom.getUomConfigId());
					obj.put("name", uom.getUomName());
					obj.put("childUomId", uom.getChildUOMId());
					obj.put("childUomIds", uom.getChildUomIds());
					obj.put("childUomNames", uom.getChildUomNames());
					results.add(obj);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public JSONObject uomConfigurationList(int configId) {
		JSONObject obj = new JSONObject();
		try {
			Optional<UOMConfiguration> opt = uomConfigRepo.findById(configId);
			if (opt.isPresent()) {
				UOMConfiguration uom = opt.get();
				obj.put("id", uom.getUomConfigId());
				obj.put("name", uom.getUomName());
				obj.put("childUomId", uom.getChildUOMId());
				obj.put("childUomIds", uom.getChildUomIds());
				obj.put("childUomNames", uom.getChildUomNames());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
