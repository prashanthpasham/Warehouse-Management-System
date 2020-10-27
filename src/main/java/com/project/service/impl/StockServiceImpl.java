package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.InventoryBatchRepository;
import com.project.dao.InventorySerialRepository;
import com.project.dao.MasterLookUpRepository;
import com.project.dao.StockLookUpRepository;
import com.project.dao.StockReceiptRepository;
import com.project.dao.StockRecieptSkusRepository;
import com.project.dao.StockRecieptSkusTrackRepository;
import com.project.dao.StockRepository;
import com.project.dao.UomConfigRepository;
import com.project.dao.WarehouseInventoryDetailsRepository;
import com.project.dao.WarehouseInventoryRepository;
import com.project.dao.WarehouseRepository;
import com.project.dto.SalesDto;
import com.project.dto.SerialBatchDto;
import com.project.dto.StockDto;
import com.project.dto.StockRecieptDto;
import com.project.dto.StockRecieptSkusDto;
import com.project.pojo.InventoryBatchDetails;
import com.project.pojo.InventorySerialDetails;
import com.project.pojo.MasterLookUp;
import com.project.pojo.Sales;
import com.project.pojo.Stock;
import com.project.pojo.StockLookUp;
import com.project.pojo.StockReciept;
import com.project.pojo.StockRecieptSkus;
import com.project.pojo.StockRecieptSkusTrack;
import com.project.pojo.UOMConfiguration;
import com.project.pojo.Warehouse;
import com.project.pojo.WarehouseInventory;
import com.project.pojo.WarehouseInventoryDetails;
import com.project.service.intf.StockServiceIntf;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class StockServiceImpl implements StockServiceIntf {
	@Autowired
	private StockLookUpRepository stockLookupRepo;
	@Autowired
	private MasterLookUpRepository masterLookupRepo;
	@Autowired
	private UomConfigRepository uomConfigRepo;
	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private WarehouseRepository warehouseRepo;
    @Autowired
    private StockRecieptSkusRepository stockRecieptSkusRepo;
    @Autowired
    private StockRecieptSkusTrackRepository stockRecieptTrackRepo;
    @Autowired
    private  WarehouseInventoryRepository warehouseInventoryRepo;
    @Autowired
    private WarehouseInventoryDetailsRepository warehouseInvDetRepo;
    @Autowired
    private  InventorySerialRepository inventorySerialRepo;
    @Autowired
    private InventoryBatchRepository inventoryBatchRepo;
    @Autowired
    private StockReceiptRepository stockReceiptRepo;
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

	@Override
	public String createStockReciept(StockRecieptDto dto) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			StockReciept reciept = new StockReciept();
			reciept.setCreatedDate(new Date());
			reciept.setSalesPerson(dto.getSalesPerson());
			reciept.setWarehouse(warehouseRepo.findById(dto.getWarehouseId()).get());
			stockReceiptRepo.save(reciept);
			List<WarehouseInventoryDetails> inventorySkus = new ArrayList<WarehouseInventoryDetails>();
			for (StockRecieptSkusDto sku : dto.getSkus()) {
				StockRecieptSkus stock = new StockRecieptSkus();
				stock.setPack(sku.getPack());
				stock.setPackQty(sku.getPackQty());
				stock.setQuantity(sku.getQuantity());
				Stock st=stockRepo.findById(sku.getStockId()).get();
				stock.setStock(st);
				stock.setStockReciept(reciept);
				stockRecieptSkusRepo.save(stock);
				WarehouseInventoryDetails det = new WarehouseInventoryDetails();
				det.setStock(st);
				det.setGoodQty(sku.getQuantity());
				List<InventorySerialDetails> serialList = new ArrayList<InventorySerialDetails>();
				List<InventoryBatchDetails> batchList = new ArrayList<InventoryBatchDetails>();
				if(sku.getTrackDetails()!=null && sku.getTrackDetails().size()>0)
				{
					for (SerialBatchDto trackDto : sku.getTrackDetails()) {
						StockRecieptSkusTrack track = new StockRecieptSkusTrack();
						track.setStockRecieptSkus(stock);
						track.setManagedBy(trackDto.getManagedBy());
						track.setPack(trackDto.getPack());
						track.setPackQty(trackDto.getQty());
						track.setQuantity(trackDto.getQty());
						track.setSerialOrBatchNo(trackDto.getBatchSerialNo());
						stockRecieptTrackRepo.save(track);
						if (trackDto.getManagedBy() != null) {
							if (trackDto.getManagedBy().equalsIgnoreCase("serial")) {
								InventorySerialDetails serial = new InventorySerialDetails();
								serial.setCreatedDate(new Date());
								serial.setExpireDate(sdf.parse(trackDto.getExpireDate()));
								serial.setSerialNo(trackDto.getBatchSerialNo());
								serial.setStatus("pending");
								serial.setManfacturedDate(sdf.parse(trackDto.getManfactureDate()));
								serialList.add(serial);
							}
							if (trackDto.getManagedBy().equalsIgnoreCase("batch")) {
								InventoryBatchDetails batch = new InventoryBatchDetails();
								batch.setAvailableQty(trackDto.getQty());
								batch.setBatchNo(trackDto.getBatchSerialNo());
								batch.setExpireDate(sdf.parse(trackDto.getExpireDate()));
								batch.setManfactureDate(sdf.parse(trackDto.getManfactureDate()));
								batchList.add(batch);
							}
						}
					}
				}
				det.setInventorySerialList(serialList);
				det.setInventoryBatchList(batchList);
				inventorySkus.add(det);
			}
			return saveWarehouseInventory(reciept.getWarehouse(), inventorySkus);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	public String saveWarehouseInventory(Warehouse warehouse,List<WarehouseInventoryDetails> inventorySkus) {
		String result="";
		try {
			WarehouseInventory inventory = warehouseInventoryRepo.findByWarehouseId(warehouse.getWarehouseId());
			System.out.println("inventory1>>"+inventory);
			if (inventory == null) {
				inventory = new WarehouseInventory();
				inventory.setCreatedDate(new Date());
				inventory.setWarehouse(warehouse);
			} else {
				inventory.setModifiedDate(new Date());
			}
			warehouseInventoryRepo.save(inventory);
			for (WarehouseInventoryDetails skus : inventorySkus) {
				WarehouseInventoryDetails details =null;
				if(inventory.getInventoryId()!=null) {
				details=warehouseInvDetRepo
						.findByStockIdAndInventoryId(skus.getStock().getStockId(), inventory.getInventoryId());
				}
				if (details != null) {
					details.setGoodQty(details.getGoodQty() + skus.getGoodQty());
				} else {
					details = skus;
				}
				details.setInventory(inventory);
				warehouseInvDetRepo.save(details);
				if (details.getInventorySerialList() != null && !details.getInventorySerialList().isEmpty()) {
					for (InventorySerialDetails serial : details.getInventorySerialList()) {
						serial.setInventoryDetails(details);
						inventorySerialRepo.save(serial);
					}
				}
				if (details.getInventoryBatchList() != null && !details.getInventoryBatchList().isEmpty()) {
					for (InventoryBatchDetails batch : details.getInventoryBatchList()) {
						batch.setInventoryDetails(details);
						inventoryBatchRepo.save(batch);
					}
				}
			}
			result="success";
		}catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return result;
	}

	@Override
	public String createSales(SalesDto dto) {
		try {
		 	Sales sales = new Sales();
		 	//sales.setCustomer();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
