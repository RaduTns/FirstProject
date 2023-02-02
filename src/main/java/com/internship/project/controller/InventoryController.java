package com.internship.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.internship.project.controller.dto.DTOMapper;
import com.internship.project.controller.dto.InventoryDTO;
import com.internship.project.dao.InventoryDAOImpl;
import com.internship.project.exceptions.CustomException;
import com.internship.project.model.Inventory;

@Stateless
public class InventoryController {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(InventoryController.class);
	@Inject
	InventoryDAOImpl inventoryDAOImpl;

	private static final Logger LOG = LogManager.getLogger(InventoryController.class);

	public InventoryDTO getByInvNr(String invNr) {
		InventoryDTO returnedItemDTO = new InventoryDTO();
		boolean bool = true;
		try {
			for (int i = 0; i < invNr.length(); i++) {
				if (!Character.isDigit(invNr.charAt(i))) {
					bool = false;
					break;
				}
			}
			if (bool == true) {
				Inventory returnedItem = inventoryDAOImpl.getByInvNr(invNr);
				DTOMapper dtoMapper = new DTOMapper();
				returnedItemDTO = dtoMapper.toDto(returnedItem);
				LOG.info("Successfully retrieved an item based on its inventory number");
			} else {
				throw new CustomException("Wrong type Exception");
			}
		} catch (CustomException e) {
			LOG.error("Error when retrieving an item based on its inventory number: " + e.getMessage());
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
			LOG.info("Successfully retrieved all items in DB");
		} catch (Exception e) {
			LOG.error("Error when retrieving all items from DB: ", e);
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
			LOG.info("Item deleted succesfully");
		} catch (SQLException e) {
			LOG.error("Error when trying to delete an item: ", e);
		}
	}

	public void addProduct(String string) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Inventory inventory = new Inventory();
		try {
			JsonElement jelem = gson.fromJson(string, JsonElement.class);
			inventory = gson.fromJson(jelem, Inventory.class);
			inventoryDAOImpl.save(inventory);
			LOG.info("Item created succesfully");
		} catch (SQLException e) {
			LOG.error("Error when trying to create an item: ", e);
		}

	}

	public String getInvNrFromJson(String string) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Inventory inventory = new Inventory();
		try {
			JsonElement jelem = gson.fromJson(string, JsonElement.class);
			inventory = gson.fromJson(jelem, Inventory.class);
			return inventory.getInventoryNumber();

		} catch (Error e) {
			// LOGGER.log(Level.SEVERE, "Error when adding a new instance to DataBase");
			return "";
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
