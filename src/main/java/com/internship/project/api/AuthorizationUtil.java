package com.internship.project.api;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;

public class AuthorizationUtil {

	TokenVerifier tokenVerifier;

	// swagger
	public static boolean isAuthorized(String headerParam) throws VerificationException {
		String[] splitHeader = headerParam.split(" ", 2);
		String keycloakToken = splitHeader[1];
		AccessToken accessToken = TokenVerifier.create(keycloakToken, AccessToken.class).getToken();
		if (accessToken.issuedFor.equals(KeycloakUtil.getClientId()) == true)
			return accessToken.isActive();
		else
			return false;

	}

}
