package com.meatwork.core.test.service;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */

import com.meatwork.core.api.di.Service;
import jakarta.inject.Inject;

@Service
public class AnServiceImpl2 implements AnService2 {

	private final AnService anService;

	@Inject
	public AnServiceImpl2(AnService anService) {
		this.anService = anService;
	}

	@Override
	public boolean isRun() {
		return anService.isRun();
	}
}
