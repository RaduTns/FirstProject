package com.internship.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.internship.project.exceptions.GetByInvNrException;
import com.internship.project.model.Inventory;

public class InventoryDAOImpl implements InventoryDAO {
	@PersistenceContext
	EntityManager em;

	private static final Logger LOG = LogManager.getLogger(InventoryDAOImpl.class);

	@Override
	public Inventory getByInvNr(String inventorynumber) {

		Inventory inventory;
		if (em.find(Inventory.class, inventorynumber) != null) {
			LOG.info("Successfully retrieved the DB instance");
			inventory = em.find(Inventory.class, inventorynumber);
			return inventory;
		} else {
			throw new GetByInvNrException("Inventory item with inventoryNumber " + inventorynumber + " not found");

		}

	}

	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}

	@Override
	public List<Inventory> getAll() {
		List<Inventory> inventories = new ArrayList<Inventory>();
		try {
			inventories = castList(Inventory.class, em.createQuery("Select f from inventory f").getResultList());
			LOG.info("Successfully retrieved all DB instances");
		} catch (Exception e) {
			if (e instanceof SQLException)
				LOG.error("Error when trying to retrieve all DB Instances: ", e);
		}

		return inventories;
	}

	public List<Inventory> filterCostCenter(String costCenter) {
		List<Inventory> filteredInventories = new ArrayList<>();
		try {
			filteredInventories = castList(Inventory.class,
					em.createQuery("Select f from inventory f where f.costCenter like :filteredValue")
							.setParameter("filteredValue", costCenter).getResultList());
			LOG.info("Successfully retrieving costCenters");
		} catch (Exception e) {
			LOG.error("Error when trying to retrieve costCenter: ", e);
		}
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

	public Inventory getInventory(String inventoryNumber) {
		return em.find(Inventory.class, inventoryNumber);
	}

	public List<Inventory> getTableView() {

		List<Inventory> inventories = new ArrayList<Inventory>();
		try {
			inventories = castList(Inventory.class,
					em.createNativeQuery("Select f from inventory_view f").getResultList());
			LOG.info("Table view retrieved");
		} catch (Exception e) {
			LOG.error("Error when trying to retrieve table: ", e);
		}

		return inventories;
	}
}
