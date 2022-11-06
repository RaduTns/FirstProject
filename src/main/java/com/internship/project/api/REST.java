package com.internship.project.api;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.internship.project.controller.InventoryController;
import com.internship.project.controller.dto.InventoryDTO;

@Path("/api")
public class REST {

	@Inject
	InventoryController inventoryController;

	@GET
	@Path("/inventoryItem/{inventoryNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam(value = "inventoryNumber") String id) {

		if (inventoryController.getByInvNr(id).getInventoryNumber() == null)
			return Response.status(404).entity(null).build();
		else
			return Response.status(200).entity(inventoryController.getByInvNr(id)).build();
	}

	@GET
	@Path("/inventoryItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInventoryItems() {
		java.util.List<InventoryDTO> inventoryItems = inventoryController.getAll();
		if (inventoryItems.isEmpty())
			return Response.status(404).entity(null).build();
		else
			return Response.status(200).entity(inventoryController.getAll()).build();
	}

	@DELETE
	@Path("/inventoryItem/{inventoryNumber}")
	public void delete(@PathParam(value = "inventoryNumber") String id) {

		inventoryController.deleteByInvNr(id);
	}

	@POST
	@Path("/inventoryItem")
	public void create(String string) {
		inventoryController.addProduct(string);
	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public void test() {
		inventoryController.getTableView();
	}
}
