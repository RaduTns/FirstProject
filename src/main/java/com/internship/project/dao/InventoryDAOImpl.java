package com.internship.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.internship.project.model.Inventory;

public class InventoryDAOImpl implements InventoryDAO {
	@PersistenceContext
	EntityManager em;
	// private final static Logger LOGGER =
	// Logger.getLogger(InventoryDAOImpl.class.getName()); efectiv nu merge :(
	// private static final Logger LOG =
	// LoggerFactory.getLogger(InventoryDAOImpl.class);

	@Override
	public Inventory getByInvNr(String inventorynumber) {

		Inventory inventory;
		try {
			inventory = em.find(Inventory.class, inventorynumber);
			// LOG.info("Successfully retrieved the DB instance");
			// LOG.error("Test pentru verificare");
			return inventory;
		} catch (Exception e) {
			inventory = null;
			// LOG.error("Error when trying to retrieve a DB instance by Inventory Number:
			// ", e);
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
			// LOG.info("Successfully retrieved all DB instances");
		} catch (Exception e) {
			// LOG.error("Error when trying to retrieve all DB Instances: {}", e);
		}

		return inventories;
	}

	public List<Inventory> filterCostCenter(String costCenter) {
		List<Inventory> filteredInventories = new ArrayList<>();
		try {
			filteredInventories = castList(Inventory.class,
					em.createQuery("Select f from inventory f where f.costCenter like :filteredValue")
							.setParameter("filteredValue", costCenter).getResultList());
			// LOG.info("Successfully retrieving costCenters");
		} catch (Exception e) {
			// LOG.error("Error when trying to retrieve costCenter: ", e);
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

	public List<Inventory> getTableView() {

		List<Inventory> inventories = new ArrayList<Inventory>();
		try {
			inventories = castList(Inventory.class,
					em.createNativeQuery("Select f from inventory_view f").getResultList());
			// LOG.info(Level.INFO, "Working as intended");
		} catch (Exception e) {
			// LOG.error(Level.SEVERE, "error: ", e);
		}

		return inventories;
	}
}
