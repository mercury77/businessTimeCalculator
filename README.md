# businessTimeCalculator
A utility to calculate working seconds/minutes/hours/days between two given dates typically Service Level Agreement

		OperationAttributes attributi = new OperationAttributeBuilder()
				.withDates(new String[] {"2020-03-02T09:00:00.000Z","2020-03-02T18:00:00.000Z",
                                {"2022-11-07T09:00:00.000Z","2022-11-09T18:00:00.000Z"})
				.withBusinessHours(new String[] { "09:00:00.000", "18:00:00.000" })
				.withPrecision(true)
				.withDayHours(9)
				.createAttribute();
		
		
		EntryClass ec = new EntryClass(attributi);
		ec.executeCalc();
    
    You can provide optionally BusinessHours ,Precision, or DayHours only .withDates parameter is mandatory.
    
    
