package com.meatwork.tools.service;

import com.meatwork.tools.api.ApplicationDown;
import com.meatwork.tools.event.Event;

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
