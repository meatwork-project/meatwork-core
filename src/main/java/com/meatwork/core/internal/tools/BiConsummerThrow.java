package com.meatwork.core.internal.tools;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@FunctionalInterface
public interface BiConsummerThrow {
	void run(Class<?> applicationCls, String[] args) throws Exception;
}
