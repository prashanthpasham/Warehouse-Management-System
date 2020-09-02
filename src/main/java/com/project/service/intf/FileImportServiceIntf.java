package com.project.service.intf;

import com.project.pojo.UploadFileHistory;

public interface FileImportServiceIntf {
	public void pickFileFromFTPFolder();

	public String saveUploadFile(UploadFileHistory uploadFile);
}
