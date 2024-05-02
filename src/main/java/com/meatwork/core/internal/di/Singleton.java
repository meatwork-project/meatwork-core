package com.meatwork.core.internal.di;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class Singleton<T> implements Factory<T> {

	private final String key;
	private Object object;
	private final Class<?> clazz;

	public Singleton(Class<?> interfaceClass, Class<?> clazz) {
		this.key = interfaceClass.getName();
		this.clazz = clazz;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get() {
		if (object == null) {
			object = Injector.create(clazz);
		}
		return (T) object;
	}

	@Override
	public String getKey() {
		return key;
	}
}
