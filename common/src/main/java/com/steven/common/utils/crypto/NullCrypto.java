package com.steven.common.utils.crypto;

public class NullCrypto implements Crypto {

	@Override
	public String encrypt(String message) {
		return message;
	}

	@Override
	public String encrypt(String message, String key) {
		return message;
	}

	@Override
	public String decrypt(String encryptedData) {
		return encryptedData;
	}

	@Override
	public String decrypt(String encryptedData, String key) {
		return encryptedData;
	}
	
}
