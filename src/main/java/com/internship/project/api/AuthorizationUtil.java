package com.internship.project.api;

import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;

public class AuthorizationUtil {

	TokenVerifier tokenVerifier;

	// swagger
	public static boolean isAuthorized(String keycloakToken) throws VerificationException {
		AccessToken accessToken = TokenVerifier.create(keycloakToken, AccessToken.class).getToken();
		return accessToken.isActive();

	}

	public static boolean isIssued(String keycloakToken) throws VerificationException {
		AccessToken accessToken = TokenVerifier.create(keycloakToken, AccessToken.class).getToken();
		System.out.println(accessToken.issuedFor);
		System.out.println(KeycloakUtil.getClientId());
		if (accessToken.issuedFor.equals(KeycloakUtil.getClientId()) == true) {
			return true;
		} else {
			return false;
		}
	}
}
