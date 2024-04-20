package com.meatwork.tools.api.scanner;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class ClassScanner {

	private ClassScanner() {
	}

	public static Set<Class<?>> getByAnnotation(Class<? extends Annotation> annotation) {
		return ModuleLayer
				.boot()
				.modules()
				.stream()
				.flatMap(module -> {
					Reflections reflections = new Reflections(new ConfigurationBuilder()
							                                          .forPackages(module
									                                                       .getPackages()
									                                                       .toArray(String[]::new))
							                                          .addClassLoaders(module.getClassLoader())
							                                          .addScanners(Scanners.TypesAnnotated));
					Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);
					return annotatedClasses.stream();
				})
				.collect(Collectors.toSet());
	}

}
