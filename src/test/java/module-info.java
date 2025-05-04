/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
module com.meatwork.core.test {

	requires com.meatwork.core;
	requires org.junit.jupiter.api;

	exports com.meatwork.core.test.service;

	opens com.meatwork.core.test;
	opens com.meatwork.core.test.service;
}