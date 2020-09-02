package com.project.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.dao.RoleRepository;
import com.project.dao.UploadFileHistoryRepository;
import com.project.pojo.Role;
import com.project.pojo.UploadFileHistory;
import com.project.service.intf.FileImportServiceIntf;
import com.project.util.ExcelUtil;

@Service
@Transactional
public class FileImportServiceImpl implements FileImportServiceIntf {
	@Autowired
	private UploadFileHistoryRepository uploadRepository;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private Environment env;

	public void pickFileFromFTPFolder() {
		try {
			List<UploadFileHistory> uploadList = uploadRepository.uploadFiles("pending");
			if (!uploadList.isEmpty()) {
				String path = env.getProperty("file.import.path");
				for (UploadFileHistory history : uploadList) {
					File file = new File(path + "/" + history.getFileName());
					if (file != null) {
						processFilesByModuleName(history, file);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void processFilesByModuleName(UploadFileHistory upload, File file) {
		try {
			String moduleName = upload.getModuleName();
			List<String> headers = ExcelUtil.templateHeadersByModule(moduleName);
			List<String> fileHeaders = ExcelUtil.readHeaderFromXLSXFile(file);
			boolean flag = true;
			for (String header : headers) {
				int index = 0;
				for (String head : fileHeaders) {
					if (header.equals(head)) {
						index = 1;
						break;
					}
				}
				if (index == 0) {
					flag = false;
				}
			}
			if (flag) {
				List<Map<String, String>> dataMap = ExcelUtil.readDataFromXLSXFile(file);
				if (!dataMap.isEmpty()) {
					if (moduleName.equalsIgnoreCase("Role")) {
						System.out.println("dataMap>>"+dataMap.toString());
						for (Map<String, String> map : dataMap) {
							if (map.get("Role_Name*") != null && map.get("Role_Name*").toString().trim().length() > 0) {
								Role r = new Role();
								r.setRoleName(map.get("Role_Name*").toString().trim());
								if (map.get("Role_Description") != null
										&& map.get("Role_Description").toString().trim().length() > 0)
									r.setDescription(map.get("Role_Description").toString().trim());
								r.setCreatedDate(new Date());
								roleRepo.save(r);
							}
						}

						upload.setStatus("completed");
					}
				} else {
					upload.setReason("Empty File");
					upload.setStatus("completed");
				}
			} else {
				upload.setReason("Template Headers Wrong");
				upload.setStatus("failed");

			}
			upload.setProcessDate(new Date());
			saveUploadFile(upload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String saveUploadFile(UploadFileHistory uploadFile) {
		uploadRepository.save(uploadFile);
		return "success";
	}

}
