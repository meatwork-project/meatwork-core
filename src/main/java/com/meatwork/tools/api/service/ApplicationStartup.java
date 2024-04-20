package com.meatwork.tools.api.service;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public interface ApplicationStartup {

	default int priority() {
		return 100;
	}

	void run(String[] args) throws Exception;

}
