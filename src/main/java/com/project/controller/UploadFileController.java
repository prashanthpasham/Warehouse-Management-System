package com.project.controller;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.intf.FileImportServiceIntf;
import com.project.util.ExcelUtil;

@RestController
@RequestMapping("/upload-file")
public class UploadFileController {
@Autowired
private FileImportServiceIntf intf;
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
		return null;

	}
}
