package com.meatwork.core.test.service;

import com.meatwork.core.api.di.Service;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@Service
public class TotoValidator implements Validator {
	@Override
	public boolean run() {
		return true;
	}
}
