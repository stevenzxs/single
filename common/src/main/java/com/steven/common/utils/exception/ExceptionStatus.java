package com.steven.common.utils.exception;

import com.steven.common.utils.result.ResStatus;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionStatus {
    ResStatus status() default ResStatus.UNKOWN;
    String description() default "";
}
