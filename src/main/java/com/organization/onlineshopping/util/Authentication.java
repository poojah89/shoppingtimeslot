package com.organization.onlineshopping.util;

import org.apache.commons.codec.binary.Base64;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Authentication {

	public static final String JWT_SALT = "cG9vamE=";

	public static void decode(String token) throws JWTDecodeException, JWTVerificationException, TokenExpiredException {

		log.info("inside decode method");

		byte[] secret = Base64.decodeBase64(JWT_SALT);

		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm).acceptLeeway(300).build();
		DecodedJWT jwt = verifier.verify(token);

	}

}
