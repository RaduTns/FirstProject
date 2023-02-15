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
import com.internship.project.exceptions.AddProductException;
import com.internship.project.exceptions.DeleteByInvNrException;
import com.internship.project.exceptions.GetAllException;
import com.internship.project.exceptions.GetByInvNrException;
import com.internship.project.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

//authorizations = { @Authorization(value = "basicAuth") } in @api
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
	@Path("/inventoryItem/{inventoryNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam(value = "inventoryNumber") String id) throws VerificationException {
		String headerParam = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		System.out.print("***********" + headerParam);
		String[] splitHeader = headerParam.split(" ", 2);
		String token = splitHeader[1];
		InventoryDTO inventory = null;
		if (AuthorizationUtil.isIssued(token) && AuthorizationUtil.isAuthorized(token)) {
			try {
				inventory = inventoryController.getByInvNr(id);
				if (inventory != null)
					return Response.status(200).entity(inventory).build();
				else
					return Response.status(404).build();
			} catch (GetByInvNrException e) {
				return Response.status(500).build();
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

		String headerParam = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		String[] splitHeader = headerParam.split(" ", 2);
		String token = splitHeader[1];
		java.util.List<InventoryDTO> inventoryItems = null;
		if (AuthorizationUtil.isAuthorized(token) == true && AuthorizationUtil.isIssued(token) == true) {
			try {
				inventoryItems = inventoryController.getAll();
				return Response.status(200).entity(inventoryItems).build();
			} catch (GetAllException e) {
				return Response.status(500).build();
			}
		} else {
			return Response.status(401).entity(null).build();
		}
	}

	@ApiOperation(value = "")
	@DELETE
	@Path("/inventoryItem/{inventoryNumber}")
	public Response delete(@PathParam(value = "inventoryNumber") String id)
			throws VerificationException, NotFoundException {

		String headerParam = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
		String[] splitHeader = headerParam.split(" ", 2);
		String token = splitHeader[1];
		InventoryDTO inventory = inventoryController.getByInvNr(id);
		if (AuthorizationUtil.isIssued(token) == true && AuthorizationUtil.isAuthorized(token) == true) {
			if (inventory.getInventoryNumber() == null)
				return Response.status(404).entity(null).build();
			else {
				try {
					inventoryController.deleteByInvNr(id);
				} catch (DeleteByInvNrException e) {
					return Response.status(500).build();
				}
				return Response.status(204).entity(null).build();
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
		if (AuthorizationUtil.isIssued(token) == true && AuthorizationUtil.isAuthorized(token) == true) {
			if (inventory.getInventoryNumber() == null) {
				try {
					inventoryController.addProduct(string);
					return Response.status(200).entity(null).build();
				} catch (AddProductException e) {
					return Response.status(500).build();
				}
			} else
				return Response.status(405).entity(null).build();
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
