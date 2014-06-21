package org.joolzminer.examples.sip.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NumberUtils.class);
	
	public static int asInt(long num) {
		if (num < Integer.MAX_VALUE) {
			return (int) num;
		} else {
			LOGGER.warn("The value received ({}) is larger than the max int. Returning {}", num, Integer.MAX_VALUE);
			return Integer.MAX_VALUE;
		}
	}
}
