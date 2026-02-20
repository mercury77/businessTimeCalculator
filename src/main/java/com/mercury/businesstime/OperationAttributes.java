package com.mercury.businesstime;

public class OperationAttributes {
	final String[] businessHours;
	final String[] dates;
	final boolean precision;
	final int dayhours;
	final String[] holidays;

	public OperationAttributes(final String[] newbusinessHours, final String[] newDates, final boolean newPrecision,
			final int newDayhours, final String[] newHolidays) {
		this.businessHours = newbusinessHours;
		this.dates = newDates;
		this.precision = newPrecision;
		this.dayhours = newDayhours;
		this.holidays = newHolidays;

	}
}