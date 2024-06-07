package com.ende.cryptography.controller;

import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ende.cryptography.util.EncryptionUtil;

@RestController
@RequestMapping("/api")
public class EncryptionController {

	 private static final Logger logger = Logger.getLogger(EncryptionController.class.getName());
	 
	@PostMapping("/encrypt")
	public ResponseEntity<String> encrypt(@RequestBody String data){
		try {
			String encryptedData = EncryptionUtil.encrypt(data);
			return ResponseEntity.ok(encryptedData);
		}catch (Exception e) {
			logger.severe("Error encrypting data: " + e.getMessage());
			return ResponseEntity.status(500).body("Error encrypting data: ");
		}
	}
	
	@PostMapping("/decrypt")
	public ResponseEntity<String> decrypt(@RequestBody String encryptedData) {
        try {
            String decryptedData = EncryptionUtil.decrypt(encryptedData);
            return ResponseEntity.ok(decryptedData);
        } catch (Exception e) {
        	logger.severe("Error decrypting data: " + e.getMessage());
            return ResponseEntity.status(500).body("Error decrypting data: " + e.getMessage());
        }
    } 
}
