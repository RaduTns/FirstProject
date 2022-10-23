package com.internship.project.controller.dto;

import java.io.Serializable;

public class InventoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String costCenter;
	private String inventoryNumber;
	private String description;
	private String serialNumber;
	private String supplier;
	private String user;
	private String location;
	private String room;
	private String date;
	private String value;
	private String residualValue;

	public InventoryDTO(String costCenter, String inventoryNumber, String description, String serialNumber,
			String supplier, String user, String location, String room, String date, String value,
			String residualValue) {
		super();
		this.costCenter = costCenter;
		this.inventoryNumber = inventoryNumber;
		this.description = description;
		this.serialNumber = serialNumber;
		this.supplier = supplier;
		this.user = user;
		this.location = location;
		this.room = room;
		this.date = date;
		this.value = value;
		this.residualValue = residualValue;
	}

	public InventoryDTO() {
		super();
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(String inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getResidualValue() {
		return residualValue;
	}

	public void setResidualValue(String residualValue) {
		this.residualValue = residualValue;
	}

}
