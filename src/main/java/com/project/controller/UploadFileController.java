package com.project.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pojo.UploadFileHistory;
import com.project.service.intf.FileImportServiceIntf;
import com.project.util.ExcelUtil;

@RestController
@RequestMapping("/upload-file")
public class UploadFileController {
	@Autowired
	private FileImportServiceIntf fileServiceIntf;
    @Autowired
    private Environment env;
	@GetMapping(value = "/template")
	public ResponseEntity generateTemplate(@RequestParam("moduleName") String moduleName) {
		try {
			File file = ExcelUtil.generateTemplateByModule(moduleName);
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			header.add("Pragma", "no-cache");
			header.add("Expires", "0");

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok().headers(header).contentLength(file.length())
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();

	}

	@PostMapping(value = "/save-upload-file", consumes = "application/json")
	public ResponseEntity saveUploadFileHistory(@RequestBody String uploadData) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(uploadData, new TypeReference<Map<String, Object>>() {
			});
			String xlsx = map.get("fileData").toString().substring(map.get("fileData").toString().indexOf(",") + 1);
			InputStream inputStream = new ByteArrayInputStream(Base64.decode(xlsx.getBytes()));
			String folderPath = env.getProperty("file.import.path");
			if(!folderPath.endsWith("/"))
				folderPath+="/";
			String fileName=map.get("moduleName").toString() + sdf.format(new Date()) + ".xlsx";
			String path = folderPath + fileName;
			File folder = new File(folderPath);
			
			if(!folder.exists()){
				folder.mkdir();
			}
			File file = new File(path);
			file.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(file);
			IOUtils.copy(inputStream, outputStream);
			UploadFileHistory uploadFile = new UploadFileHistory();
			uploadFile.setFileName(fileName);
			uploadFile.setModuleName(map.get("moduleName").toString());
			uploadFile.setStatus("pending");
			uploadFile.setUploadedBy(map.get("userName").toString());
			uploadFile.setUploadedDate(new Date());
			String response = fileServiceIntf.saveUploadFile(uploadFile);
			if (response.equals("success")) {
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();

	}
}
