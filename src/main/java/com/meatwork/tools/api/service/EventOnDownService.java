package com.meatwork.tools.api.service;

import com.meatwork.tools.api.service.ApplicationDown;
import com.meatwork.tools.api.event.Event;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class EventOnDownService implements ApplicationDown {
	@Override
	public void run() {
		Event.close();
	}
}
