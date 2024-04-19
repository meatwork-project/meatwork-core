import com.google.inject.Module;
import com.meatwork.tools.module.ToolsModule;

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

	exports com.meatwork.tools.event;
	exports com.meatwork.tools.di;
	exports com.meatwork.tools.scanner;
	exports com.meatwork.tools.service;
	exports com.meatwork.tools.api;

	provides Module with ToolsModule;
}