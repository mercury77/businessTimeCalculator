package com.mercury.businesstime;

public class OperationAttributeBuilder {
	/* mandatory */
	private String[] newDates;
	/* non mandatory */
	private boolean newPrecision = false;
	private String[] newBusinessHours = { "09:00:00.000", "18:00:00.000" };
	private int newDayHours = 9;

	public OperationAttributeBuilder() {
	}

	public OperationAttributeBuilder withBusinessHours(String[] newBusinessHours) {
		this.newBusinessHours = newBusinessHours;
		return this;
	}

	public OperationAttributeBuilder withDates(String[] newDates) {
		this.newDates = newDates;
		return this;
	}

	public OperationAttributeBuilder withPrecision(boolean newPrecision) {
		this.newPrecision = newPrecision;
		return this;
	}

	public OperationAttributeBuilder withDayHours(int newDayHours) {
		this.newDayHours = newDayHours;
		return this;
	}

	public OperationAttributes createAttribute() {
		return new OperationAttributes(newBusinessHours, newDates, newPrecision, newDayHours);
	}

}