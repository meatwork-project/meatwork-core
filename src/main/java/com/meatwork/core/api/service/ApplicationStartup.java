package com.meatwork.core.api.service;

import com.meatwork.core.api.di.IService;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@IService(scope = IService.Scope.MULTIPLE, mandatory = false)
public interface ApplicationStartup {
	default int priority() {
		return 100;
	}
	void run(Class<?> ApplicationCls, String[] args) throws Exception;
}
