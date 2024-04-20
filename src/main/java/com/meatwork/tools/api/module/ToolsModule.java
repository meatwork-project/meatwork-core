package com.meatwork.tools.api.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.meatwork.tools.api.service.ApplicationDown;
import com.meatwork.tools.api.service.EventOnDownService;

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
