package com.internship.project.controller.dto;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class InventoryDTOTest {

	private static final Logger LOG = LoggerFactory.getLogger(InventoryDTOTest.class);

	@Test
	void test() {

		LOG.debug("Test pe debug");
		LOG.error("Test");
		LOG.info("Logger is working as intended");
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setCostCenter("sdasdasdas");
		inventoryDTO.setDate("sdasdasdsa");
		inventoryDTO.setDescription("dsadsadasdas");
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		System.out.println(gson.toJson(inventoryDTO));

	}

	@Test
	public void test1() {

	}

}
