package com.meatwork.core.api.di;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
	enum Scope {
		SINGLETON,
		BY_INSTANCE
	}

	Scope scope() default Scope.SINGLETON;
}
