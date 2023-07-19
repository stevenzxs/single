package com.steven.common.utils.crypto.cipher;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Base64Codec {

	private static Logger LOG = LoggerFactory.getLogger(Base64Codec.class);

	private static String ENCODING = "UTF-8";

	public static String encode(String raw) {
		return encodeByBase64URLSafe(encodeByTable(raw));
	}

	public static String decode(String encode) {
		return decodeByTable(decodeByBase64(encode));
	}

	public static String encodeByTable(String value) {
		try {
			byte[] allbytes = value.getBytes(ENCODING);
			for (int i = 0; i < allbytes.length; i++) {
				allbytes[i] = bEnCodeTable[(allbytes[i] & 0xFF)];
			}
			return new String(allbytes, ENCODING);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		}
		return "";
	}

	public static String decodeByTable(String encryptString) {
		try {
			byte[] bytesDeCode = encryptString.getBytes(ENCODING);
			for (int i = 0; i < bytesDeCode.length; i++) {
				bytesDeCode[i] = bDeCodeTable[(bytesDeCode[i] & 0xFF)];
			}
			return new String(bytesDeCode, ENCODING);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		}
		return "";
	}

	public static String encodeByBase64URLSafe(String data) {
		return Base64.encodeBase64URLSafeString(data.getBytes(StandardCharsets.UTF_8));
	}

	public static String decodeByBase64(String encode) {
		return new String(Base64.decodeBase64(encode.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
	}
	
	public static String decodeByBase64UTF8(String encode) {
		try {
			return new String(Base64.decodeBase64(encode.getBytes(StandardCharsets.UTF_8)),ENCODING);
		} catch (UnsupportedEncodingException e) {
			LOG.error("解密异常！", e);
		}
		return "";
	}
	
	static final byte[] bEnCodeTable = { 0, 113, 1, 12, 105, 67, 2, 94,
			100, 112, 10, 54, 68, 13, 9, 82, 91, 73, 47, 28, 19, 29, 72, 57,
			34, 77, 48, 107, 80, 111, 35, 18, 65, 38, 25, 3, 88, 40, 49, 115,
			62, 76, 70, 109, 41, 30, 85, 64, 4, 108, 44, 124, 81, 104, 50, 74,
			92, 79, 21, 118, 69, 116, 102, 126, 55, 22, 84, 127, 58, 95, 51,
			11, 121, 20, 26, 61, 59, 101, 23, 63, 83, 56, 45, 89, 24, 93, 32,
			39, 98, 123, 14, 37, 106, 8, 27, 86, 119, 33, 122, 110, 114, 46,
			120, 42, 16, 97, 52, 66, 53, 17, 78, 43, 99, 117, 90, 103, 31, 87,
			96, 6, 60, 36, 75, 71, 5, 15, 7, 125, -125, -117, -118, -114, -120,
			-124, -121, -127, -122, -123, -126, -128, -113, -116, -115, -119,
			-102, -105, -98, -111, -99, -100, -109, -101, -108, -103, -104,
			-107, -106, -110, -112, -97, -92, -85, -91, -84, -90, -89, -96,
			-94, -86, -83, -82, -95, -93, -87, -88, -81, -73, -68, -77, -70,
			-65, -78, -79, -75, -66, -72, -67, -76, -69, -71, -74, -80, -50,
			-56, -58, -51, -62, -53, -52, -49, -55, -54, -61, -59, -60, -57,
			-64, -63, -40, -48, -34, -43, -47, -33, -46, -45, -44, -36, -42,
			-41, -39, -38, -37, -35, -32, -31, -30, -29, -28, -27, -26, -25,
			-24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12,
			-11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1 };

	static final byte[] bDeCodeTable = { 0, 2, 6, 35, 48, 124, 119, 126,
			93, 14, 10, 71, 3, 13, 90, 125, 104, 109, 31, 20, 73, 58, 65, 78,
			84, 34, 74, 94, 19, 21, 45, 116, 86, 97, 24, 30, 121, 91, 33, 87,
			37, 44, 103, 111, 50, 82, 101, 18, 26, 38, 54, 70, 106, 108, 11,
			64, 81, 23, 68, 76, 120, 75, 40, 79, 47, 32, 107, 5, 12, 60, 42,
			123, 22, 17, 55, 122, 41, 25, 110, 57, 28, 52, 15, 80, 66, 46, 95,
			117, 36, 83, 114, 16, 56, 85, 7, 69, 118, 105, 88, 112, 8, 77, 62,
			115, 53, 4, 92, 27, 49, 43, 99, 29, 9, 1, 100, 39, 61, 113, 59, 96,
			102, 72, 98, 89, 51, 127, 63, 67, -117, -121, -118, -128, -123,
			-119, -120, -122, -124, -113, -126, -127, -115, -114, -125, -116,
			-98, -109, -99, -106, -104, -101, -100, -111, -102, -103, -112,
			-105, -107, -108, -110, -97, -90, -85, -89, -84, -96, -94, -92,
			-91, -82, -83, -88, -95, -93, -87, -86, -81, -65, -74, -75, -78,
			-69, -73, -66, -80, -71, -67, -77, -68, -79, -70, -72, -76, -50,
			-49, -60, -54, -52, -53, -62, -51, -63, -56, -55, -59, -58, -61,
			-64, -57, -47, -44, -42, -41, -40, -45, -38, -37, -48, -36, -35,
			-34, -39, -33, -46, -43, -32, -31, -30, -29, -28, -27, -26, -25,
			-24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12,
			-11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1 };

}
