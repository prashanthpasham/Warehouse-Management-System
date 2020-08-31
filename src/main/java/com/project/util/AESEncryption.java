package com.project.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author sekher AESEncryption uses AES Alogrithm with 128 Key size to emcrypt
 *         and decrypt data.
 *
 */

public class AESEncryption {

	public static int KEY_SIZE = 128;
	Cipher encryptCipher = null;
	Cipher decryptCipher = null;
	static AESEncryption instance = null;

	public static AESEncryption getInstance() {
		return instance;
	}

	public static void init(String encodedKey) throws Exception {
		instance = new AESEncryption(encodedKey, "AES/CBC/PKCS5Padding");
	}

	public AESEncryption(String encodedKey, String paddingString) throws Exception {
		byte key[] = Base64.getDecoder().decode(encodedKey);
		// byte key[] = fixSecret(encodedKey,16);
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		// encryptCipher = Cipher.getInstance("AES");
		encryptCipher = Cipher.getInstance(paddingString);

		encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(new byte[16]));
		// decryptCipher = Cipher.getInstance("AES");
		decryptCipher = Cipher.getInstance(paddingString);
		decryptCipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(new byte[16]));
	}

	public AESEncryption(String encodedKey) throws Exception {
		byte key[] = Base64.getDecoder().decode(encodedKey);
		// byte key[] = fixSecret(encodedKey,16);
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		encryptCipher = Cipher.getInstance("AES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
		decryptCipher = Cipher.getInstance("AES");
		decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);
	}

	public String encode(String input) throws Exception {
		byte result[] = encryptCipher.doFinal(input.getBytes());
		String encodedResult = Base64.getEncoder().encodeToString(result);
		return encodedResult;
	}

	public String decode(String encodedInput) throws Exception {
		byte input[] = Base64.getDecoder().decode(encodedInput);
		// byte input[] = fixSecret(encodedInput,32);
		// logger.info("==== "+input.length);
		byte result[] = decryptCipher.doFinal(input);
		return new String(result, "utf-8");
	}

	private byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
		if (s.length() < length) {
			int missingLength = length - s.length();
			for (int i = 0; i < missingLength; i++) {
				s += " ";
			}
		}

		// logger.info("fixSecret :::"+s+"****"+(s.substring(0,
		// length).getBytes()));
		return s.substring(0, length).getBytes("UTF-8");
	}

	public static String generateKey() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(KEY_SIZE);
		SecretKey secertKey = generator.generateKey();
		byte key[] = secertKey.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(key);
		return encodedKey;

	}

	public static void main(String[] args) {
		try {
			AESEncryption.init("t7GcYbbdbKxZtV2ge6qpeQ==");

			String input = "malawi#welcome";
			String encodedString = AESEncryption.getInstance().encode(input);
			// logger.info(encodedString);
            System.out.println(encodedString);
			String decodedString = AESEncryption.getInstance().decode(encodedString);
			// logger.info(decodedString);
			 System.out.println(decodedString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
