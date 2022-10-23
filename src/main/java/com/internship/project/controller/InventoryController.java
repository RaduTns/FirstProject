package com.internship.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.internship.project.controller.dto.DTOMapper;
import com.internship.project.controller.dto.InventoryDTO;
import com.internship.project.dao.InventoryDAOImpl;
import com.internship.project.model.Inventory;

@Stateless
public class InventoryController {

	private final static Logger LOGGER = Logger.getLogger(InventoryController.class.getName());
	@Inject
	InventoryDAOImpl inventoryDAOImpl;

	public InventoryDTO getByInvNr(String invNr) {
		InventoryDTO returnedItemDTO = new InventoryDTO();
		try {
			Inventory returnedItem = inventoryDAOImpl.getByInvNr(invNr);
			DTOMapper dtoMapper = new DTOMapper();
			returnedItemDTO = dtoMapper.toDto(returnedItem);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error: ", e);
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
			LOGGER.log(Level.SEVERE, "Error: ", e);
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
			LOGGER.log(Level.SEVERE, "Error: ", e);
		}
		return filteredInventoriesDTO;
	}

	public void deleteByInvNr(String invNr) {
		try {
			Inventory returnedItem = inventoryDAOImpl.getByInvNr(invNr);
			inventoryDAOImpl.delete(returnedItem);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error when deleting an item by its inventory number " + invNr + ": ", e);
		}
	}
}
