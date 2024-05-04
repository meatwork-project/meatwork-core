package com.meatwork.core.internal.di;

import com.meatwork.core.api.di.IService;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class Injector {

	private static final Logger LOGGER = LoggerFactory.getLogger(Injector.class);

	@SuppressWarnings("unchecked")
	public static  <T> T create(Class<?> clazz) {
		try {
			boolean needInjection = true;
			Constructor<?> constructor = Arrays
					.stream(clazz.getConstructors())
					.filter(it -> it.isAnnotationPresent(Inject.class))
					.findFirst()
					.orElse(null);

			if(constructor == null) {
				needInjection = false;
				constructor = clazz.getConstructor();
			}

			if (needInjection) {
				List<Object> args = new ArrayList<>();
				Parameter[] parameters = constructor.getParameters();
				for (Parameter parameter : parameters) {
					Class<?> type = parameter.getType();
					if (type.isAssignableFrom(Set.class)) {
						Object actualTypeArgument = ((ParameterizedType) parameter.getParameterizedType()).getActualTypeArguments()[0];
						Class<?> actualTypeClass;
						if (actualTypeArgument instanceof ParameterizedType parameterType) {
							actualTypeClass = (Class<?>) parameterType.getRawType();
						} else {
							actualTypeClass = (Class<?>) actualTypeArgument;
						}

						Factory<?> factory = ObjectGraph.get(actualTypeClass);
						if(!(factory instanceof MultiBinding<?>)) {
							LOGGER.error("type {} is not a multi-binding", type);
							throw new RuntimeException("type " + type + " is not a multi-binding");
						}
						args.add(factory.get());
					} else if (type.isInterface()){
						Factory<?> factory = ObjectGraph.get(type);

						if (factory == null && type.getAnnotation(IService.class).mandatory()) {
							String message = "Cannot found class of type " + type.getName();
							LOGGER.error(message);
							throw new RuntimeException(message);
						} else if (factory == null && !type.getAnnotation(IService.class).mandatory()) {
							args.add(null);
						} else {
							args.add(factory.get());
						}
					} else {
						args.add(Injector.create(type));
					}
				}
				return (T) constructor.newInstance(args.toArray(Object[]::new));
			}
			return (T) constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			LOGGER.error("cannot instantiate {} - {}", clazz.getName(), e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
