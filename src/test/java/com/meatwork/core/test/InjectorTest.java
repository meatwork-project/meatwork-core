package com.meatwork.core.test;

import com.meatwork.core.api.di.CDI;
import com.meatwork.core.api.di.Service;
import com.meatwork.core.test.service.AnService2;
import com.meatwork.core.test.service.Validator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class InjectorTest {

	@Test
	@Disabled
	public void testInject() {
		ValidatorSet validator = CDI.get(ValidatorSet.class);
		Assertions.assertNotNull(validator);
		Assertions.assertTrue(validator.getValidatorSet().stream().allMatch(Validator::run));
	}

	@Test
	@Disabled
	public void testInhjectDeep() {
		AnService2 anService2 = CDI.get(AnService2.class);
		Assertions.assertNotNull(anService2);
		Assertions.assertTrue(anService2.isRun());
	}

	@Service
	public static class ValidatorSet {

		private final Set<Validator> validatorSet;

		@Inject
		public ValidatorSet(Set<Validator> validatorSet) {
			this.validatorSet = validatorSet;
		}

		public Set<Validator> getValidatorSet() {
			return validatorSet;
		}
	}

}
