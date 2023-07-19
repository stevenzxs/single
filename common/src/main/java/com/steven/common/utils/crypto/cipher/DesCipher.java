package com.steven.common.utils.crypto.cipher;

import com.steven.common.utils.crypto.Crypto;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
public class DesCipher {
	private static final String ALGORITHM = "DES/ECB/PKCS5Padding";
	
	private static final Logger LOG = LoggerFactory.getLogger(DesCipher.class);
	
	/**
	 * 采用默认密钥加密数据
	 * @param message
	 * @return
	 */
	public static String encrypt(String message){
		return encrypt(message, Crypto.DEFAULT_SECRET_KEY);
	}

	private static String fillChar(String key){
		return CipherHelper.fillChar(key, 8);
	}
	
	/**
	 * 指定密钥加密数据
	 * @param message 待加密的数据
	 * @param key 密钥
	 * @return
	 */
	public static String encrypt(String message,String key){
		try {
	        // 从原始密匙数据创建DESKeySpec对象
	        DESKeySpec dks = new DESKeySpec(fillChar(key).getBytes("UTF-8"));
	        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
	        // 一个SecretKey对象
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher对象实际完成加密操作
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        // 用密匙初始化Cipher对象
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, new SecureRandom());
	        // 现在，获取数据并加密
	        // 正式执行加密操作
	        byte[] encVal = cipher.doFinal(message.getBytes("UTF-8"));
	        String encryptedValue = Base64.encodeBase64String(encVal);
			return encryptedValue;
			
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error(e.getMessage());
		}
		return message;
	}

	/**
	 * 采用默认密钥解密数据
	 * @param encryptedData
	 * @return
	 */
	public static String decrypt(String encryptedData) {
		return decrypt(encryptedData, Crypto.DEFAULT_SECRET_KEY);
	}
	
	/**
	 * 指定密钥解密数据
	 * @param encryptedData 待解密的数据
	 * @param key 密钥
	 * @return 解密后的数据
	 */
	public static String decrypt(String encryptedData, String key) {
		try {
	        // 从原始密匙数据创建一个DESKeySpec对象
	        DESKeySpec dks = new DESKeySpec(fillChar(key).getBytes("UTF-8"));
	        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
	        // 一个SecretKey对象
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher对象实际完成解密操作
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        // 用密匙初始化Cipher对象
	        cipher.init(Cipher.DECRYPT_MODE, securekey, new SecureRandom());
	        byte[] decordedValue = Base64.decodeBase64(encryptedData);
			byte[] decValue = cipher.doFinal(decordedValue);
			String decryptedValue = new String(decValue);
			return decryptedValue;
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error(e.getMessage());
			LOG.error("\r\n密文和密钥不匹配\r\n或者采用高版本的密码包解密低版本密码包的密文产生的错误，需要采用当前版本的密码包重新产生密文");
		} catch (BadPaddingException e) {
			LOG.error(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return encryptedData;
	}
}
