package com.steven.common.utils.crypto.cipher;

import com.steven.common.utils.crypto.Crypto;
import com.steven.common.utils.crypto.cipher.sm4.SM4;
import com.steven.common.utils.crypto.cipher.sm4.SM4_Context;
import com.steven.common.utils.crypto.cipher.sm4.Util;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

/**
 * SM4算法包 支持ECB和CBC模式，原文填充方式支持空格和分组缺失长度，结果编码支持base64和hexstring
 * 
 * @date 2019-5-29
 */
public class SM4Cipher {

	public enum Type {BASE64, HEXSTRING};

	/**
	 * 默认ECB
	 * @param message
	 * @return
	 */
	public static String encrypt(String message) {
		return encrypt(message, Crypto.DEFAULT_SECRET_KEY);
	}

	/**
	 * 默认ECB
	 * @param encryptedData
	 * @return
	 */
	public static String decrypt(String encryptedData){
		return decrypt(encryptedData, Crypto.DEFAULT_SECRET_KEY);
	}

	public static String encrypt(String message, String key) {
		SM4_Context ctx = getContext(SM4.SM4_ENCRYPT);
		return encrypt_ecb(message, key ,ctx);
	}

	public static String decrypt(String encryptedData, String key) {
		SM4_Context ctx = getContext(SM4.SM4_DECRYPT);
		return decrypt_ecb(encryptedData,key,ctx);
	}

	public static SM4_Context getContext(int mode) {
		SM4_Context ctx = new SM4_Context();
		ctx.isPadding = true;
//		ctx.mode = SM4.SM4_DECRYPT; / SM4_ENCRYPT
		ctx.mode = mode;
		ctx.paddingMode = SM4.PADDINGLENGTH;
		ctx.isHexStringKey = false;
		ctx.convertMode = Type.BASE64;
		return ctx;
	}


	private static String encrypt_ecb(String message,String key, SM4_Context ctx) {
		try {
			byte[] keyBytes = getKeyBytes(key,ctx.isHexStringKey);

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_ecb(ctx, message.getBytes(StandardCharsets.UTF_8));
			String cipherText = "";
			if (ctx.convertMode == Type.BASE64) {
				cipherText = DatatypeConverter.printBase64Binary(encrypted);
			} else {
				cipherText = Util.getHexString(encrypted, false);
			}

			return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
			return message;
		}
	}

	private static String decrypt_ecb(String message,String key, SM4_Context ctx) {
		try {
			byte[] keyBytes = getKeyBytes(key,ctx.isHexStringKey);

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] encrypted = null;
			if (ctx.convertMode == Type.BASE64) {
				encrypted = DatatypeConverter.parseBase64Binary(message);
			} else {
				encrypted = Util.hexStringToBytes(message);
			}
			byte[] decrypted = sm4.sm4_crypt_ecb(ctx, encrypted);
			return new String(decrypted, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			return message;
		}
	}

	public static String encrypt_cbc(String message,String key,String iv, SM4_Context ctx) {
		try {
			byte[] keyBytes = getKeyBytes(key, ctx.isHexStringKey);
			byte[] ivBytes = getKeyBytes(iv, ctx.isHexStringKey);

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			String cipherText = null;
			byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, message.getBytes(StandardCharsets.UTF_8));
			if (ctx.convertMode == Type.BASE64) {
				cipherText = DatatypeConverter.printBase64Binary(encrypted);
			} else {
				cipherText = Util.getHexString(encrypted, false);
			}
			return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
			return message;
		}
	}

	public static String decrypt_cbc(String message,String key,String iv, SM4_Context ctx) {
		try {
			byte[] keyBytes = getKeyBytes(key, ctx.isHexStringKey);
			byte[] ivBytes = getKeyBytes(iv, ctx.isHexStringKey);

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);

			byte[] encrypted = null;
			if (ctx.convertMode == Type.BASE64) {
				encrypted = DatatypeConverter.parseBase64Binary(message);
			}else{
				encrypted = Util.hexStringToBytes(message);
			}

			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, encrypted);

			return new String(decrypted, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			return message;
		}
	}

	private static byte[] getKeyBytes(String key, boolean isHexStringKey) {
		if (key == null) {
			return null;
		}
		if (isHexStringKey) {
			return Util.hexStringToBytes(key);
		} else {
			return key.getBytes(StandardCharsets.UTF_8);
		}
	}
}
