package com.meatwork.tools.api.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.meatwork.tools.api.di.CDI;
import com.meatwork.tools.api.scanner.ClassScanner;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public final class Event {

	private static final EventBus eventBus = new EventBus();
	private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

	static {
		for (Class<?> aClass : ClassScanner.getByAnnotation(EventListener.class)) {
			for (Method declaredMethod : aClass.getDeclaredMethods()) {
				if (declaredMethod.isAnnotationPresent(Subscribe.class)) {
					eventBus.register(CDI.get(aClass));
				}
			}
		}

	}

	public static void register(Object register) {
		eventBus.register(register);
	}

	private Event() {
	}

	public static <T> void broadcast(T event) {
		if (event
				.getClass()
				.isAnnotationPresent(Synchronizer.class)) {
			eventBus.post(event);
		} else {
			executorService.submit(() -> eventBus.post(event));
		}
	}

	public static void close() {
		executorService.close();
	}
}
