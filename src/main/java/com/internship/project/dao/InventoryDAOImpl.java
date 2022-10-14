package com.internship.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.internship.project.model.Inventory;

@Path("/api")
public class InventoryDAOImpl implements InventoryDAO {
	@PersistenceContext
	EntityManager em;
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	// StoredProcedureQuery query =
	// this.em.createNamedStoredProcedureQuery("returnrow");

	@Override
	public Inventory getByInvNr(String inventorynumber) throws SQLException {
		return em.find(Inventory.class, inventorynumber);
	}

	@GET
	@Path("/inventoryItems")
	@Override
	public List<Inventory> getAll() throws SQLException {
		List<Inventory> inventories = new ArrayList<>();
		inventories = em.createQuery("Select f from inventory f").getResultList();
		LOGGER.info("--------------------All Users retrieved--------------------");
		return inventories;
	}

	public List<Inventory> filterCostCenter(String costCenter) throws SQLException {
		List<Inventory> filteredInventories = new ArrayList<>();
		filteredInventories = em.createQuery("Select f from inventory f where f.costCenter like :filteredValue")
				.setParameter("filteredValue", costCenter).getResultList();
		LOGGER.info("-------------------" + costCenter + " type retrieved--------------- ");
		return filteredInventories;
	}

	@Override
	public void save(Inventory inventory) throws SQLException {
		em.persist(inventory);
	}

	@Override
	public Inventory update(Inventory inventory) throws SQLException {
		return em.merge(inventory);

	}

	@Override
	public void delete(Inventory inventory) throws SQLException {
		em.remove(inventory);
	}

	@GET
	@Path("/inventoryItem/{inventorynumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Inventory getInventory(@PathParam(value = "inventorynumber") String inventoryNumber) {
		return em.find(Inventory.class, inventoryNumber);
	}

}
