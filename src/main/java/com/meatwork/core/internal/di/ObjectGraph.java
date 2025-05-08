package com.meatwork.core.internal.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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

	@SuppressWarnings("unchecked")
	public static <T extends Factory<?>> T getOrDefault(Class<?> clazz, Class<T> defaultFactory) {
		T factory = (T) map.get(clazz.getName());
		if (factory == null) {
            try {
                return defaultFactory.getConstructor(Class.class).newInstance(clazz);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                LOGGER.error("Cannot create default factory for class {}", clazz.getName(), e);
                throw new RuntimeException(e);
            }
        }
		return factory;
	}

}
