package com.meatwork.core.internal.tools;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@FunctionalInterface
public interface ConsummerThrow {
	void run(String[] args) throws Exception;
}
