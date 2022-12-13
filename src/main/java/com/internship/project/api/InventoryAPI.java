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

import org.keycloak.common.VerificationException;

import com.internship.project.controller.InventoryController;
import com.internship.project.controller.dto.InventoryDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Path("/api")
@Api(tags = { "inventory" })
@SwaggerDefinition(tags = { @Tag(name = "inventory") })
@Produces(MediaType.APPLICATION_JSON)

public class InventoryAPI {

	@Inject
	InventoryController inventoryController;

	@Context
	HttpHeaders httpHeaders;

	KeycloakUtil keycloakUtils;

	@ApiOperation(value = "")
	@GET
	@Path("/inventoryItem/{inventoryNumber}/{authorization}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam(value = "inventoryNumber") String id,
			@PathParam(value = "authorization") String auth, @Context final HttpHeaders httpHeaders)
			throws VerificationException {
		// String headerParam =
		// httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		String headerParam = auth;
		System.out.print("***********" + headerParam);
		String[] splitHeader = headerParam.split(" ", 2);
		String token = splitHeader[1];
		if (AuthorizationUtil.isIssued(token)) {
			if (AuthorizationUtil.isAuthorized(token)) {
				if (inventoryController.getByInvNr(id).getInventoryNumber() == null)
					return Response.status(404).entity(null).build();
				else
					return Response.status(200).entity(inventoryController.getByInvNr(id)).build();
			} else {
				return Response.status(401).entity(null).build();
			}
		} else {
			return Response.status(401).entity(null).build();
		}
	}

	@ApiOperation(value = "Retrieve all items")
	@GET
	@Path("/inventoryItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInventoryItems() throws VerificationException {

//		String headerParam = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
//		String[] splitHeader = headerParam.split(" ", 2);
//		String token = splitHeader[1];
		java.util.List<InventoryDTO> inventoryItems = inventoryController.getAll();
//		if (AuthorizationUtil.isIssued(token) == true) {
//			if (AuthorizationUtil.isAuthorized(token) == true) {
//				if (inventoryItems.isEmpty())
//					return Response.status(404).entity(null).build();
//				else
		return Response.status(200).entity(inventoryController.getAll()).build();
//			} else {
//				return Response.status(401).entity(null).build();
//			}
//		} else {
//			return Response.status(401).entity(null).build();
//		}

	}

	@ApiOperation(value = "")
	@DELETE
	@Path("/inventoryItem/{inventoryNumber}")
	public Response delete(@PathParam(value = "inventoryNumber") String id) throws VerificationException {

		String headerParam = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		String[] splitHeader = headerParam.split(" ", 2);
		String token = splitHeader[1];
		InventoryDTO inventory = inventoryController.getByInvNr(id);
		if (AuthorizationUtil.isIssued(token) == true) {
			if (AuthorizationUtil.isAuthorized(token) == true) {
				if (inventory.getInventoryNumber() == null)
					return Response.status(404).entity(null).build();
				else {
					inventoryController.deleteByInvNr(id);
					return Response.status(204).entity(null).build();
				}
			} else {
				return Response.status(401).entity(null).build();
			}
		} else {
			return Response.status(401).entity(null).build();
		}

	}

	@ApiOperation(value = "")
	@POST
	@Path("/inventoryItem")
	public Response create(String string) throws VerificationException {

		String headerParam = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		String[] splitHeader = headerParam.split(" ", 2);
		String token = splitHeader[1];
		String inventoryNumber = inventoryController.getInvNrFromJson(string);
		InventoryDTO inventory = inventoryController.getByInvNr(inventoryNumber);
		if (AuthorizationUtil.isIssued(token) == true) {
			if (AuthorizationUtil.isAuthorized(token) == true) {
				if (inventory.getInventoryNumber() == null) {
					inventoryController.deleteByInvNr(inventoryNumber);
					inventoryController.addProduct(string);
					return Response.status(201).entity(null).build();
				} else
					return Response.status(405).entity(null).build();
			} else {
				return Response.status(401).entity(null).build();
			}
		} else {
			return Response.status(401).entity(null).build();
		}

	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public void test() {
		inventoryController.getTableView();
	}
}