package com.internship.project.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.internship.project.controller.InventoryController;
import com.internship.project.controller.dto.InventoryDTO;

@Named
@SessionScoped
public class InventoryView implements Serializable {

	@Inject
	InventoryController inventoryController;
	private List<InventoryDTO> inventoryList = new ArrayList<>();
	private List<String> costCenterList = new ArrayList<>();
	private String selectedCostCenter;

	public String getSelectedCostCenter() {
		return selectedCostCenter;
	}

	public void setSelectedCostCenter(String selectedCostCenter) {
		this.selectedCostCenter = selectedCostCenter;
	}

	@PostConstruct
	public void init() {
		costCenterList.add("All");
		costCenterList.add("A10000737");
		costCenterList.add("A10000738");
		costCenterList.add("A10000739");
		selectedCostCenter = costCenterList.get(0);
		inventoryList = inventoryController.getAll();
	}

	public void costCenterChanged() {
		if (selectedCostCenter.equals("All")) {
			inventoryList = inventoryController.getAll();
		} else {
			inventoryList = inventoryController.filterCostCenter(selectedCostCenter);
		}
	}

	public List<InventoryDTO> filteredInventory(String value) {
		return inventoryController.filterCostCenter(value);
	}

	public List<InventoryDTO> getInventoryList() {
		return inventoryList.stream().sorted(Comparator.comparing(InventoryDTO::getInventoryNumber))
				.collect(Collectors.toList());

	}

	public void setInventoryList(List<InventoryDTO> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public void printInventoryDetails(Integer id, String nume, String prenume, String facultate, String contact) {
		System.out.println("Detalii: ID: " + id);
		System.out.println("Nume: " + nume);
		System.out.println("Prenume: " + prenume);
		System.out.println("Facultate: " + facultate);
		System.out.println("Contact: " + contact);
	}

	public List<String> getCostCenterList() {
		return costCenterList;
	}

	public void setCostCenterList(List<String> costCenterList) {
		this.costCenterList = costCenterList;
	}

}
