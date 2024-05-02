package com.meatwork.core.internal.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class ObjectGraph {

	private final static Logger LOGGER = LoggerFactory.getLogger(ObjectGraph.class);

	private static final Map<String, Factory<?>> map = new HashMap<>();

	private ObjectGraph() {}

	public static void register(Factory<?> factory) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					"Registering factory: {}",
					factory.getKey()
			);
		}
		map.put(factory.getKey(), factory);
	}

	public static Factory<?> get(Class<?> clazz) {
		return map.get(clazz.getName());
	}

}
