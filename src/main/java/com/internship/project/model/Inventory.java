package com.internship.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inventory")
@Entity(name = "inventory")
//@NamedStoredProcedureQuery(
//		name = "returnrow", 
//		procedureName = "returnrow",
//		parameters = {
//				@StoredProcedureParameter(mode = ParameterMode.INOUT, type = String.class, name = "_val")
//		}
//)
public class Inventory {
	@Column(name = "costcenter")
	private String costCenter;
	@Id
	@Column(name = "inventorynumber")
	private String inventoryNumber;
	private String description;
	@Column(name = "serialnumber")
	private String serialNumber;
	private String supplier;
	@Column(name = "userr")
	private String user;
	@Column(name = "locationn")
	private String location;
	private String room;
	@Column(name = "datee")
	private String date;
	@Column(name = "valuee")
	private String value;
	private String residualValue;

	public Inventory(String costCenter, String inventoryNumber, String description, String serialNumber,
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

	public Inventory() {
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

	@Override
	public String toString() {
		return "Inventory [costCenter=" + costCenter + ", inventoryNumber=" + inventoryNumber + ", description="
				+ description + ", serialNumber=" + serialNumber + ", supplier=" + supplier + ", user=" + user
				+ ", location=" + location + ", room=" + room + ", date=" + date + ", value=" + value
				+ ", residualValue=" + residualValue + "]";
	}

}
