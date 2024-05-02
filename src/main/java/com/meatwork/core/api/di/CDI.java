package com.meatwork.core.api.di;

import com.meatwork.core.internal.di.Factory;
import com.meatwork.core.internal.di.MultiBinding;
import com.meatwork.core.internal.di.ObjectGraph;
import com.meatwork.core.internal.di.Singleton;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public final class CDI {

	static {
		List<String> list = ModuleLayer
				.boot()
				.modules()
				.stream()
				.flatMap(it -> it
						.getPackages()
						.stream())
				.toList();
		Reflections reflections = new Reflections(list);
		Set<Class<?>> subTypesOf = reflections.getTypesAnnotatedWith(Service.class);
		for (Class<?> aClass : subTypesOf) {
			if (aClass.getInterfaces().length > 0) {
				for (Class<?> anInterface : aClass.getInterfaces()) {
					IService annotationsByType = anInterface.getAnnotation(IService.class);
					if(annotationsByType.scope().equals(IService.Scope.MULTIPLE)) {
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
						ObjectGraph.register(new Singleton(anInterface, aClass));
					}
				}
			} else {
				ObjectGraph.register(new Singleton(aClass, aClass));
			}
		}
	}

	private CDI() {}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> clazz) {
		return (T) ObjectGraph.get(clazz).get();
	}

}
