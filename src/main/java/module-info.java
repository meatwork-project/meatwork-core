import com.google.inject.Module;
import com.meatwork.tools.api.module.ToolsModule;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
module com.meatwork.tools {
	requires com.google.common;
	requires com.google.guice;
	requires jakarta.inject;
	requires org.slf4j;
	requires org.reflections;

	uses Module;

	exports com.meatwork.tools.api.event;
	exports com.meatwork.tools.api.di;
	exports com.meatwork.tools.api.scanner;
	exports com.meatwork.tools.api.service;

	provides Module with ToolsModule;
}