package com.meatwork.tools.api.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public final class CDI {

	private static final Logger LOGGER = LoggerFactory.getLogger(CDI.class);

	private final static Injector injector;

	static {
		List<Module> list = ServiceLoader
				.load(Module.class)
				.stream()
				.map(ServiceLoader.Provider::get)
				.toList();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Module list : {}",
			             list
					             .stream()
					             .map(it -> it
							             .getClass()
							             .getName())
					             .collect(Collectors.joining(", "))
			);
		}

		injector = Guice.createInjector(list);
	}

	private CDI() {
	}

	public static <T> T get(Class<T> clazz) {
		return injector.getInstance(clazz);
	}

}
