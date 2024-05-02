package com.meatwork.core.internal.di;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public interface Factory<T> {
	T get();
	String getKey();
}
