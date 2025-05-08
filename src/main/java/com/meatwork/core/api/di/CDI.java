package com.meatwork.core.api.di;

import com.meatwork.core.api.service.MeatworkApplication;
import com.meatwork.core.internal.di.Factory;
import com.meatwork.core.internal.di.MultiBinding;
import com.meatwork.core.internal.di.ObjectGraph;
import com.meatwork.core.internal.di.Singleton;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public final class CDI {

	private static final Logger LOGGER = LoggerFactory.getLogger(CDI.class);

	public static void init(Class<?> applicationCls) {
		MeatworkApplication annotation = applicationCls.getAnnotation(MeatworkApplication.class);

		if(annotation == null) {
            LOGGER.error("No MeatworkApplication annotation found for main class {}", applicationCls.getName());
			return;
		}

		var list = new ArrayList<>(Arrays.asList(annotation.packages()));
		list.add("com.meatwork");
		Reflections reflections = new Reflections(list);
		Set<Class<?>> subTypesOf = reflections.getTypesAnnotatedWith(Service.class);
		LOGGER.debug("service {} found", subTypesOf);

		for (Class<?> aClass : subTypesOf) {
			if (aClass.getInterfaces().length > 0) {
				for (Class<?> anInterface : aClass.getInterfaces()) {
					IService annotationsByType = anInterface.getAnnotation(IService.class);
					if(annotationsByType != null && annotationsByType.scope().equals(IService.Scope.MULTIPLE)) {
						Factory<?> factory = ObjectGraph.get(anInterface);
						MultiBinding<?> multiBinding;
						if(factory != null) {
							multiBinding = (MultiBinding<?>) factory;
						} else {
							multiBinding = new MultiBinding<>(anInterface);
						}
						multiBinding.addBinding(aClass);
						ObjectGraph.register(multiBinding);
					} else {
						ObjectGraph.register(new Singleton<>(anInterface, aClass));
					}
				}
			} else {
				ObjectGraph.register(new Singleton<>(aClass, aClass));
			}
		}
	}

	private CDI() {}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> clazz) {
		return (T) Optional.of(ObjectGraph.get(clazz)).map(Factory::get).orElse(null);
	}

}
