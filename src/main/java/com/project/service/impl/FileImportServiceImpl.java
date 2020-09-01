package com.project.service.impl;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.dao.UploadFileHistoryRepository;
import com.project.pojo.UploadFileHistory;
import com.project.service.intf.FileImportServiceIntf;
@Service
@Transactional
public class FileImportServiceImpl implements FileImportServiceIntf {
	@Autowired
	private UploadFileHistoryRepository uploadRepository;
    @Autowired
    private Environment env;

	public void pickFileFromFTPFolder() {
		try {

			List<UploadFileHistory> uploadList = uploadRepository.uploadFiles("pending");
			if (!uploadList.isEmpty()) {
				String path=env.getProperty("file.import.path");
				for (UploadFileHistory history : uploadList) {
					File file = new File(path+history.getFilePath());
                    if(file!=null){
                    	processFilesByModuleName(history,file);
                    }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void processFilesByModuleName(UploadFileHistory upload,File file){
		try{
			String moduleName = upload.getModuleName();
			if(moduleName.equalsIgnoreCase("Role")){
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
