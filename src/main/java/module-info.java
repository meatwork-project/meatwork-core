/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
module com.meatwork.core {
	requires org.reflections;
	requires transitive org.slf4j;
	requires transitive jakarta.inject;
	exports com.meatwork.core.api.di;
	exports com.meatwork.core.api.service;
}