package com.organization.onlineshopping.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.organization.onlineshopping.exception.ApiError;
import com.organization.onlineshopping.util.Authentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component

@Order(5)
public class JWTFilterConfig extends OncePerRequestFilter {

	private static final String TOKEN_NAME = "Authorization";

	private static final String TOKEN_PREFIX = "Bearer";

	private static String[] NO_TOKEN = new String[] { "index", "activation", "actuator", "push" };

	private static boolean stringContainsItemFromList(String inputString, String[] items) {
		return Arrays.stream(items).parallel().anyMatch(inputString::contains);

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("Initiating filter");

		String path = request.getServletPath();
		if (path.contains("swagger") || path.contains("docs") ) {

			filterChain.doFilter(request, response);
			return;

		}

		if (!stringContainsItemFromList(path, NO_TOKEN)) {

			String Xauth = request.getHeader(TOKEN_NAME);

			if (Xauth == null) {
				setErrorResponse(HttpStatus.BAD_REQUEST, response);
				// response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization Header
				// is missing !");
				return;
			}

			if (Xauth.startsWith(TOKEN_PREFIX)) {

				Xauth = Xauth.substring(7);
			}

			try {
				Authentication.decode(Xauth);
				logger.info("Token verify success");

			} catch (Exception e) {
				setErrorResponse(HttpStatus.BAD_REQUEST, response, e);
				// response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage());
				return;
			}
		}

		// }

		filterChain.doFilter(request, response);

		log.info("ending filter");
	}

	private void setErrorResponse(HttpStatus status, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setStatus(400);
		response.setContentType("application/json");
		// A class used for errors
		ApiError apiError = new ApiError(400, "Authorisation header is missing", "Invalid Header");
		apiError.setOutcomeCode(400);
		apiError.setOutcomeMessage("Authorisation header is missing");
		apiError.setInternalMessage("Invalid Header");

		try {
			String json = apiError.convertToJson();
			response.getWriter().write(json);
		} catch (IOException e) {
			log.info("context", e);
		}
	}

	private void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) {
		response.setStatus(400);
		response.setContentType("application/json");
		// A class used for errors
		ApiError apiError = new ApiError(status, ex);
		apiError.setOutcomeCode(400);
		apiError.setOutcomeMessage(ex.getMessage());
		apiError.setInternalMessage("Invalid Token");

		try {
			String json = apiError.convertToJson();
			response.getWriter().write(json);
		} catch (IOException e) {
			log.info("context", e);
		}
	}

}