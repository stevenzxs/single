package com.steven.common.utils.crypto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加密算法的注解
 * @author MeiKefu
 * @date 2015-11-24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CryptoInst {
	String[] value() default {};
}