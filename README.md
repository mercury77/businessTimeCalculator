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
    
    You can provide optionally BusinessHours ,Time Precision, or DayHours only .withDates() parameter is mandatory.
    
    Minimum configuration reuired :
    
    
		OperationAttributes attributi = new OperationAttributeBuilder()
				.withDates(new String[] {"2020-03-02T09:00:00.000Z","2020-03-02T18:00:00.000Z"})
				.createAttribute();
				
		EntryClass ec = new EntryClass(attributi);
		ec.executeCalc();
    
   By definition:
   Normal Business Hours means an eight (8) hour period between 6:00 am and 8:00 pm, Monday thru Friday, unless otherwise agreed to in writing by the parties.
   by setting .withBusinessHours() you will be able to change this range.
   e.g .withBusinessHours(new String[] { "06:00:00.000", "20:00:00.000" })
   
   Time Precision will let you enable or disable calculation precision .
   e.g .withPrecision(true) 
   will calculates how many Days Hours Minutes and Seconds beetween two given dates, with false no seconds will be calculated
   
   Day Hours is the amount of hours that define your working Day e.g 08.00 A.M to 05.00 P.M can be 8 hours period.
   .withDayHours(8)
   
   Dates :
   
   You can provide a set of two dates or multiple set of two dates results will bu sum up.
   
   e.g .withDates(new String[] {"2020-03-02T09:00:00.000Z","2020-03-02T18:00:00.000Z"})
   
   e.g   .withDates(new String[] {"2019-10-15T14:52:21.000Z","2019-10-15T14:59:21.000Z",
				"2019-10-15T15:04:33.000Z","2019-10-15T15:04:36.000Z",
				"2019-10-15T15:13:15.000Z","2019-10-15T15:13:19.000Z",
				"2019-10-15T15:14:20.000Z", "2019-10-15T15:14:31.000Z",
				"2019-10-15T15:14:58.000Z","2019-10-15T15:15:33.000Z", 
				"2019-10-15T15:15:33.000Z","2019-10-15T15:15:38.000Z",
				"2019-10-15T15:16:09.000Z","2019-10-15T15:17:26.000Z",
				"2019-10-15T15:17:35.000Z","2019-10-15T15:17:43.000Z",
				"2019-10-15T15:20:36.000Z","2019-10-15T15:35:17.000Z",
				"2019-10-15T16:10:08.000Z","2019-10-29T10:34:19.000Z",
				"2019-10-29T10:35:29.000Z","2019-10-29T10:35:47.000Z",
				"2019-10-30T10:04:41.000Z","2019-11-30T10:04:41.000Z",
				"2020-02-14T15:23:38.000Z","2020-02-14T22:42:55.000Z" })
   In this case you will retrive each delta express in minutes an seconds and a total sum up of all calculation
   
   
    
    
