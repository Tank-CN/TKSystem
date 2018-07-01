package com.tk.util.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Inherited
public @interface RpcService {

	byte inboundCompression() default 0;

	byte outboundCompression() default 0;

	int timeout() default 20;
}
