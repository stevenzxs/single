package com.steven.common.utils.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CryptoContext {

	private static Logger LOG = LoggerFactory.getLogger(CryptoContext.class);
	
	public static Map<String,Crypto> CRYPTOS = new HashMap<String,Crypto>();
	
	static{
		autoScanAnnotation();
	}
	
	public static Crypto NULL = new NullCrypto();
	
	public static Crypto getCrypto(String algorithm){
		Crypto crypto =  CRYPTOS.get(algorithm);
		return crypto==null?NULL:crypto;
	}
	
	public static void autoScanAnnotation(){
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		String basePkg = "com/steven";
		String packageSearchPath = "classpath*:"+basePkg+"/**/crypto/impl/*.class";
		try {
			Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
			for (Resource resource : resources) {
				String uri = resource.getURI().toString();
				String className = uri.substring(uri.indexOf(basePkg),uri.length()-6).replace('/', '.');
				Class cls = Class.forName(className);
				if(cls.isAnnotationPresent(CryptoInst.class)){
					String key = ( (CryptoInst)cls.getAnnotation(CryptoInst.class)).value()[0];
					CRYPTOS.put(key, (Crypto)Class.forName(className).newInstance());
				}
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
		} catch (InstantiationException e) {
			LOG.error(e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage());
		}
	}
}
