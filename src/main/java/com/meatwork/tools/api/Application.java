package com.meatwork.tools.api;

import com.meatwork.tools.di.CDI;
import com.meatwork.tools.tools.ConsummerThrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class Application {

	private final static Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void run(String[] args) {
		try {
			ApplicationStartupSet applicationStartupSet = CDI
					.get(ApplicationStartupSet.class);
			applicationStartupSet
					.getApplicationStartups()
					.stream()
					.sorted(Comparator.comparingInt(ApplicationStartup::priority))
					.forEach(it -> catcher(it::run, args));
		} catch (Exception e) {
			LOG.info("No applicationStartup detected");
		}
	}

	public static void run() {
		run(null);
	}

	private static void catcher(ConsummerThrow runnable, String[] args) {
		try {
			runnable.run(args);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
