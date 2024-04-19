package com.meatwork.tools.api;

import jakarta.inject.Inject;

import java.util.Set;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class ApplicationStartupSet {
	private final Set<ApplicationStartup> applicationStartups;

	@Inject
	public ApplicationStartupSet(Set<ApplicationStartup> applicationStartups) {
		this.applicationStartups = applicationStartups;
	}

	public Set<ApplicationStartup> getApplicationStartups() {
		return applicationStartups;
	}
}
