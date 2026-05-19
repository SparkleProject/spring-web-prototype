package com.lintech.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Md5PasswordEncoder implements PasswordEncoder {

	private static final String SALT = "9d5e3ecdeb4cdb7acfd63075ae046672";
	private static final int ITERATIONS = 2;

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] saltBytes = SALT.getBytes(StandardCharsets.UTF_8);
			byte[] sourceBytes = rawPassword.toString().getBytes(StandardCharsets.UTF_8);

			md.update(saltBytes);
			byte[] hashed = md.digest(sourceBytes);

			for (int i = 1; i < ITERATIONS; i++) {
				md.reset();
				hashed = md.digest(hashed);
			}

			return toHex(hashed);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}

	private static String toHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
}
