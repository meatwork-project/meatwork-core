/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
module com.meatwork.core {
	requires org.slf4j;
	requires org.reflections;
	requires jakarta.inject;

	exports com.meatwork.core.api.di;
	exports com.meatwork.core.api.service;
}