package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "UPLOAD_FILE_HISTORY")
@Entity
public class UploadFileHistory {
	private Integer uploadId;
	private String uploadedBy;
	private String moduleName;
	private Date uploadedDate;
	private Date processDate;
	private String status;
	private String fileName;
	private String reason;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UPLOAD_ID")
	public Integer getUploadId() {
		return uploadId;
	}

	public void setUploadId(Integer uploadId) {
		this.uploadId = uploadId;
	}

	@Column(name = "UPLOAD_BY")
	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	@Column(name = "MODULE_NAME")
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "UPLOAD_DATE")
	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	@Column(name = "PROCESSED_DATE")
	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
