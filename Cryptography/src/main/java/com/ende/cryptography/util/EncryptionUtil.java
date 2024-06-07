package com.ende.cryptography.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Logger;

public class EncryptionUtil {
	private static final Logger logger = Logger.getLogger(EncryptionUtil.class.getName());
	private static final String algorithm= "AES";
	private static final String secret_key ="1234567890123456"; //should be 16 bytes for AES-128
	
	public static String encrypt(String data) throws Exception{
		 if (secret_key.length() != 16) {
	            throw new IllegalArgumentException("Invalid key length: must be 16 bytes");
	        }
	        try {
	            SecretKeySpec key = new SecretKeySpec(secret_key.getBytes(), algorithm);
	            Cipher cipher = Cipher.getInstance(algorithm);
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte[] encrypted = cipher.doFinal(data.getBytes());
	            return Base64.getEncoder().encodeToString(encrypted);
	        } catch (Exception e) {
	            logger.severe("Encryption error: " + e.getMessage());
	            throw e;
	        }
	}
	
	public static String decrypt(String encryptedData) throws Exception {
        if (secret_key.length() != 16) {
            throw new IllegalArgumentException("Invalid key length: must be 16 bytes");
        }
        try {
            SecretKeySpec key = new SecretKeySpec(secret_key.getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(original);
        } catch (Exception e) {
            logger.severe("Decryption error: " + e.getMessage());
            throw e;
        }
    }

}
