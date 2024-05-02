package com.meatwork.core.api.service;

import com.meatwork.core.api.di.IService;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@IService(scope = IService.Scope.MULTIPLE)
public interface ApplicationDown {
	void run();
}
