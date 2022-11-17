package com.internship.project.api;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.internship.project.controller.InventoryController;
import com.internship.project.controller.dto.InventoryDTO;

@Path("/api")
public class REST {

	@Inject
	InventoryController inventoryController;

	@Context
	HttpHeaders httpHeaders;

	KeycloakUtil keycloakUtils;

	@GET
	@Path("/inventoryItem/{inventoryNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam(value = "inventoryNumber") String id) {
//verif getRequestHeader
		String headerParam = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
		String headerParam2 = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		// parsam fara Bearer
		String[] splitToken = KeycloakUtil.splitToken(headerParam);
		String header = splitToken[0];
		String payload = splitToken[1];
		String signature = splitToken[2];
		JsonObject jObjHeader = KeycloakUtil.toJson(header);
		JsonObject jObjPayload = KeycloakUtil.toJson(payload);
		if (KeycloakUtil.checkAvailability(jObjPayload.get("exp").toString()) == true) {
			if (inventoryController.getByInvNr(id).getInventoryNumber() == null)
				return Response.status(404).entity(null).build();
			else
				return Response.status(200).entity(inventoryController.getByInvNr(id)).build();
		} else {
			return Response.status(401).entity(null).build();
		}

	}

	@GET
	@Path("/inventoryItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInventoryItems() {

		String header = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
		java.util.List<InventoryDTO> inventoryItems = inventoryController.getAll();
		if (inventoryItems.isEmpty())
			return Response.status(404).entity(null).build();
		else
			return Response.status(200).entity(inventoryController.getAll()).build();
	}

	@DELETE
	@Path("/inventoryItem/{inventoryNumber}")
	public Response delete(@PathParam(value = "inventoryNumber") String id) {

		String header = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
		InventoryDTO inventory = inventoryController.getByInvNr(id);
		if (inventory.getInventoryNumber() == null)
			return Response.status(404).entity(null).build();
		else {
			inventoryController.deleteByInvNr(id);
			return Response.status(204).entity(null).build();
		}
	}

	@POST
	@Path("/inventoryItem")
	public Response create(String string) {

		String header = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);
		String inventoryNumber = inventoryController.getInvNrFromJson(string);
		InventoryDTO inventory = inventoryController.getByInvNr(inventoryNumber);
		if (inventory.getInventoryNumber() == null) {
			inventoryController.deleteByInvNr(inventoryNumber);
			inventoryController.addProduct(string);
			return Response.status(201).entity(null).build();
		} else
			return Response.status(405).entity(null).build();

	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public void test() {
		inventoryController.getTableView();
	}
}
