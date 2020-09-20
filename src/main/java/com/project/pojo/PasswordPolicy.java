package com.project.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PASSWORD_POLICY")
public class PasswordPolicy implements Serializable {
	private static final long serialVersionUID = -946367190296110132L;
	private int passwordConfigId;
	private String passwordType;
	private int pwdMinLength;
	private int pwdMaxLength;
	private int pwdExpireInDays;
	private int previousPasswordHistory;
	private int alertBeforeExpDays;
	private int accountLock;
	private int accountLockTime;
	private String upperCase;
	private String lowerCase;
	private String specialcharacters;
	private String numbers;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PASSWORD_CONFIGURATION")
	@SequenceGenerator(name = "SEQ_PASSWORD_CONFIGURATION", sequenceName = "SEQ_PASSWORD_CONFIGURATION")
	@Column(name = "password_config_id", unique = true, nullable = false, precision = 22, scale = 0)
	public int getPasswordConfigId() {
		return passwordConfigId;
	}

	public void setPasswordConfigId(int passwordConfigId) {
		this.passwordConfigId = passwordConfigId;
	}

	@Column(name = "PASSWORD_MIN")
	public int getPwdMinLength() {
		return pwdMinLength;
	}

	public void setPwdMinLength(int pwdMinLength) {
		this.pwdMinLength = pwdMinLength;
	}

	@Column(name = "PASSWORD_TYPE")
	public String getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}

	@Column(name = "PASSWORD_MAX")
	public int getPwdMaxLength() {
		return pwdMaxLength;
	}

	public void setPwdMaxLength(int pwdMaxLength) {
		this.pwdMaxLength = pwdMaxLength;
	}

	@Column(name = "PWD_EXPIRE_DAYS")
	public int getPwdExpireInDays() {
		return pwdExpireInDays;
	}

	public void setPwdExpireInDays(int pwdExpireInDays) {
		this.pwdExpireInDays = pwdExpireInDays;
	}

	@Column(name = "PREVIOUS_PASSWORD")
	public int getPreviousPasswordHistory() {
		return previousPasswordHistory;
	}

	public void setPreviousPasswordHistory(int previousPasswordHistory) {
		this.previousPasswordHistory = previousPasswordHistory;
	}

	@Column(name = "PWD_ALERT_EXPIRY")
	public int getAlertBeforeExpDays() {
		return alertBeforeExpDays;
	}

	public void setAlertBeforeExpDays(int alertBeforeExpDays) {
		this.alertBeforeExpDays = alertBeforeExpDays;
	}

	@Column(name = "ACCOUNT_ATTEMPTS")
	public int getAccountLock() {
		return accountLock;
	}

	public void setAccountLock(int accountLock) {
		this.accountLock = accountLock;
	}

	@Column(name = "ACCOUNT_LOCK_TIME")
	public int getAccountLockTime() {
		return accountLockTime;
	}

	public void setAccountLockTime(int accountLockTime) {
		this.accountLockTime = accountLockTime;
	}

	@Column(name = "UPPER_CASE")
	public String getUpperCase() {
		return upperCase;
	}

	public void setUpperCase(String upperCase) {
		this.upperCase = upperCase;
	}

	@Column(name = "LOWER_CASE")
	public String getLowerCase() {
		return lowerCase;
	}

	public void setLowerCase(String lowerCase) {
		this.lowerCase = lowerCase;
	}

	@Column(name = "SPECIAL_CHARACTERS")
	public String getSpecialcharacters() {
		return specialcharacters;
	}

	public void setSpecialcharacters(String specialcharacters) {
		this.specialcharacters = specialcharacters;
	}

	@Column(name = "NUMBERS")
	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

}
