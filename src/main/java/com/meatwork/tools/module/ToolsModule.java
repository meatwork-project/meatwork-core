package com.meatwork.tools.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.meatwork.tools.api.ApplicationDown;
import com.meatwork.tools.service.EventOnDownService;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class ToolsModule extends AbstractModule {

	@Override
	protected void configure() {
		Multibinder
				.newSetBinder(
						binder(),
						ApplicationDown.class
				)
				.addBinding()
				.to(EventOnDownService.class);
	}

}
