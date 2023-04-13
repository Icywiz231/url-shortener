package com.url.shortener.service;

import java.util.Objects;

class Base62Encoder {
	private static final String ENCODER_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public static String encode(String val) {
		long number = Math.abs(Objects.hashCode(val));
		StringBuilder stringBuilder = new StringBuilder(1);
		do {
			stringBuilder.insert(0, ENCODER_CHARS.charAt((int) (number % 62)));
			number /= 62;
		} while (number > 0);
		return stringBuilder.toString();
	}
}
