package com.meatwork.core.test.service;

import com.meatwork.core.api.di.IService;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@IService(scope = IService.Scope.MULTIPLE)
public interface Validator {
	boolean run();
}
