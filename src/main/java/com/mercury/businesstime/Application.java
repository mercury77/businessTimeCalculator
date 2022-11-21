package com.mercury.businesstime;



public class Application {
	public static long counter = 0;

	public static void main(String[] args) {

		OperationAttributes attributi = new OperationAttributeBuilder()
				.withDates(new String[] {"2022-11-11T09:31:00.000Z","2022-11-25T17:40:13.000Z"})
				.withBusinessHours(new String[] { "09:00:00.000", "18:00:00.000" })
				.withPrecision(true)
				.withDayHours(9)
				.createAttribute();
		
		
		EntryClass ec = new EntryClass(attributi);
		ec.executeCalc();
	}

}
