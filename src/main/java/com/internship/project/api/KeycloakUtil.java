package com.internship.project.api;

import java.util.Base64;

import org.keycloak.representations.AccessToken;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class KeycloakUtil {

	// TokenVerifier<JsonWebToken>
	AccessToken at;

	public static String[] splitToken(String header) {
		String[] splitHeader = header.split(" ", 2);
		String token = splitHeader[1];
		String[] parts = token.split("\\.");
		parts[0] = decode(parts[0]);
		parts[1] = decode(parts[1]);
		parts[2] = decode(parts[2]);
		return parts;

	}

	public static JsonObject toJson(String string) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement jElem = gson.fromJson(string, JsonElement.class);
		JsonObject jObj = jElem.getAsJsonObject();
		return jObj;
	}

	public static String decode(String encodedString) {
		return new String(Base64.getUrlDecoder().decode(encodedString));
	}

	public static Boolean checkAvailability(String time) {
		long toBeChecked = Long.parseLong(time);
		long unixCurrentTime = System.currentTimeMillis() / 1000L;
		if (toBeChecked <= unixCurrentTime)
			return false;
		else
			return true;

	}

	public static String getClientId() {
		return "inventory-client";
	}
}
