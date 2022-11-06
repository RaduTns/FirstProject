package com.internship.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.internship.project.controller.dto.DTOMapper;
import com.internship.project.controller.dto.InventoryDTO;
import com.internship.project.dao.InventoryDAOImpl;
import com.internship.project.model.Inventory;

@Stateless
public class InventoryController {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(InventoryController.class);
	@Inject
	InventoryDAOImpl inventoryDAOImpl;

	public InventoryDTO getByInvNr(String invNr) {
		InventoryDTO returnedItemDTO = new InventoryDTO();
		try {
			Inventory returnedItem = inventoryDAOImpl.getByInvNr(invNr);
			DTOMapper dtoMapper = new DTOMapper();
			returnedItemDTO = dtoMapper.toDto(returnedItem);
		} catch (Exception e) {
			// LOG.error("Error: ", e);
		}
		return returnedItemDTO;
	}

	public List<InventoryDTO> getAll() {
		List<InventoryDTO> inventoriesDTO = new ArrayList<>();
		try {
			List<Inventory> inventories = inventoryDAOImpl.getAll();
			DTOMapper dtoMapper = new DTOMapper();
			for (Inventory inventory : inventories) {
				inventoriesDTO.add(dtoMapper.toDto(inventory));

			}
		} catch (Exception e) {
			// LOGGER.log(Level.SEVERE, "Error: ", e);
		}
		return inventoriesDTO;
	}

	public List<InventoryDTO> filterCostCenter(String costCenter) {
		List<InventoryDTO> filteredInventoriesDTO = new ArrayList<>();
		try {
			List<Inventory> filteredInventories = inventoryDAOImpl.filterCostCenter(costCenter);
			DTOMapper dtoMapper = new DTOMapper();
			for (Inventory inventory : filteredInventories) {
				filteredInventoriesDTO.add(dtoMapper.toDto(inventory));
			}
		} catch (Exception e) {
			// LOGGER.log(Level.SEVERE, "Error: ", e);
		}
		return filteredInventoriesDTO;
	}

	public void deleteByInvNr(String invNr) {
		try {
			Inventory returnedItem = inventoryDAOImpl.getByInvNr(invNr);
			inventoryDAOImpl.delete(returnedItem);
		} catch (SQLException e) {
			// LOGGER.log(Level.SEVERE, "Error when deleting an item by its inventory number
			// " + invNr + ": ", e);
		}
	}

	public void addProduct(String string) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Inventory inventory = new Inventory();
		try {
			JsonElement jelem = gson.fromJson(string, JsonElement.class);
			JsonObject jobj = jelem.getAsJsonObject();
			inventory = gson.fromJson(jelem, Inventory.class);
			inventoryDAOImpl.save(inventory);
		} catch (SQLException e) {
			// LOGGER.log(Level.SEVERE, "Error when adding a new instance to DataBase");
		}

	}

	public List<InventoryDTO> getTableView() {
		List<InventoryDTO> inventoriesViewDTO = new ArrayList<>();
		try {
			List<Inventory> inventories = inventoryDAOImpl.getAll();
			DTOMapper dtoMapper = new DTOMapper();
			for (Inventory inventory : inventories) {
				inventoriesViewDTO.add(dtoMapper.toDto(inventory));

			}
		} catch (Exception e) {
			// LOGGER.log(Level.SEVERE, "Error when trying to retrieve table instances: ",
			// e);
		}
		return inventoriesViewDTO;
	}
}
