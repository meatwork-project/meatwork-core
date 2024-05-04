package com.meatwork.core.api.service;

import com.meatwork.core.api.di.Service;
import jakarta.inject.Inject;

import java.util.Set;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@Service
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
