package com.internship.project.controller.dto;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class InventoryDTOTest {

	@Test
	void test() {
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setCostCenter("sdasdasdas");
		inventoryDTO.setDate("sdasdasdsa");
		inventoryDTO.setDescription("dsadsadasdas");
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		System.out.println(gson.toJson(inventoryDTO));
	}

}
