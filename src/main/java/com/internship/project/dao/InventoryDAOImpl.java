package com.internship.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.internship.project.model.Inventory;

public class InventoryDAOImpl implements InventoryDAO {
	@PersistenceContext
	EntityManager em;
	private final static Logger LOGGER = Logger.getLogger(InventoryDAOImpl.class.getName());
	// StoredProcedureQuery query =
	// this.em.createNamedStoredProcedureQuery("returnrow");

	@Override
	public Inventory getByInvNr(String inventorynumber) {

		Inventory inventory;
		try {
			inventory = em.find(Inventory.class, inventorynumber);
			return inventory;
		} catch (Exception e) {
			inventory = null;
			LOGGER.log(Level.SEVERE, "error: ", e);
			return inventory;
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
			LOGGER.log(Level.INFO, "Working as intended");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "error: ", e);
		}

		return inventories;
	}

	public List<Inventory> filterCostCenter(String costCenter) {
		List<Inventory> filteredInventories = new ArrayList<>();
		try {
			filteredInventories = castList(Inventory.class,
					em.createQuery("Select f from inventory f where f.costCenter like :filteredValue")
							.setParameter("filteredValue", costCenter).getResultList());
			LOGGER.log(Level.INFO, "Working as intended");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "error getting costCenter: ", e);
		}
		return filteredInventories;
	}

	@Override
	public void save(Inventory inventory) throws SQLException {
		em.persist(inventory);
	}

	@Override
	public Inventory update(Inventory inventory) throws SQLException { // de facut post
		return em.merge(inventory);

	}

	@Override
	public void delete(Inventory inventory) throws SQLException {

		em.remove(inventory);

	}

	public Inventory getInventory(String inventoryNumber) {
		return em.find(Inventory.class, inventoryNumber);
	}

}
