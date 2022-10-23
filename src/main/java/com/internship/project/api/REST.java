package com.internship.project.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.internship.project.controller.InventoryController;
import com.internship.project.controller.dto.InventoryDTO;
import com.internship.project.model.Inventory;

@Path("/api")
public class REST {

	@Inject
	InventoryController inventoryController;

	@GET
	@Path("/inventoryItem/{inventoryNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public InventoryDTO getById(@PathParam(value = "inventoryNumber") String id) {
		Response response;
		return inventoryController.getByInvNr(id); // de schimbat in Response
	}

	@GET
	@Path("/inventoryItem")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InventoryDTO> getInventoryItems() {
		return inventoryController.getAll();
	}

	public void update(Inventory inventoryItem) {

	}

	@DELETE
	@Path("/inventoryItem/{inventoryNumber}")
	public void delete(@PathParam(value = "inventoryNumber") String id) {

		inventoryController.deleteByInvNr(id);
	}
}
