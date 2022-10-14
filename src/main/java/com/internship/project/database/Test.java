package com.internship.project.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.internship.project.dao.InventoryDAOImpl;
import com.internship.project.model.Inventory;

public class Test {

	public static void main(String[] args) throws SQLException {
		Connection con = Database.getConnection();

		if (con != null) {
			System.out.println("De folosit daca nu repar em");
		}

		InventoryDAOImpl inventoryDAO = new InventoryDAOImpl();
		List<Inventory> inventories = inventoryDAO.getAll();
		System.out.println(inventories);
		Inventory inventory = inventoryDAO.getByInvNr("709209057046");

		System.out.println(inventory);
		// System.out.println(inventoryDAO.getAll());
	}

}
