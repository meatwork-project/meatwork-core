package com.meatwork.tools.api.di;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public final class CDI {

	private final static Injector injector;

	private CDI() {
	}

	static {
		injector = Guice.createInjector(ServiceLoader
				                                .load(Module.class)
				                                .stream()
				                                .map(ServiceLoader.Provider::get)
				                                .toList());
	}

	public static <T> T get(Class<T> clazz) {
		return injector.getInstance(clazz);
	}

}
