package com.meatwork.core.internal.di;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class MultiBinding<T> implements Factory<Set<T>> {

	private final String key;
	private final Set<Class<?>> bindingClass = new HashSet<>();
	private Set<T> bindingObject;


	public MultiBinding(Class<T> interfaceClass) {
		this.key = interfaceClass.getName();
	}

	public void addBinding(Class<?> clazz) {
		bindingClass.add(clazz);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<T> get() {
		if(bindingObject == null) {
			bindingObject = (Set<T>) bindingClass.stream().map(Injector::create).collect(Collectors.toSet());
		}
		return bindingObject;
	}

	@Override
	public String getKey() {
		return key;
	}
}
