package com.mercury.businesstime;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EntryClass  {
	
	private final String[] DATES;
	private final String[] BUSINESSHOURS;
	private final boolean TIMEPRECISION;
	private final int DAYHOURS;
	
public EntryClass(OperationAttributes attributes) {
	this.DATES = attributes.dates;
	this.BUSINESSHOURS = attributes.businessHours;
	this.TIMEPRECISION = attributes.precision;
	this.DAYHOURS = attributes.dayhours;
}
public void executeCalc() {
	
	UtilityClass utilityclass = new UtilityClass(DAYHOURS);
		
	long totalseconds = 0;
	long totalminute = 0;
	long start = System.currentTimeMillis();
	int operations = DATES.length - 1;


	for (int i = 0; i < operations; i += 2) {

		Date startDate = utilityclass.createDate(DATES[i]);
		Date endDate = utilityclass.createDate(DATES[i + 1]);
		if (TIMEPRECISION) {
			long temp_seconds = utilityclass.slacalculatorSeconds(startDate, endDate, BUSINESSHOURS);
			totalseconds += temp_seconds;
			System.out.println("Rev " + i + ", Minutes , " + (temp_seconds / 60) + " , Seconds , " + temp_seconds);
		} else {
			long temp_minutes = utilityclass.slacalculatorMinute(startDate, endDate, BUSINESSHOURS);
			totalminute += temp_minutes;
			System.out.println("Rev " + i + ", Minutes , " + temp_minutes);
		}

	}

	if (TIMEPRECISION) {
		System.out.println(utilityclass.timeConvert3(totalseconds) + " (timeprecision= " + TIMEPRECISION + " )");
	} else {
		System.out
				.println(utilityclass.timeConvert2((int) totalminute) + " (timeprecision= " + TIMEPRECISION + " )");
	}
	long stop = System.currentTimeMillis();
	long elapsedtime = stop - start;
	long elapsedseconds = TimeUnit.MILLISECONDS.toSeconds(elapsedtime);

	System.out.println("Tempo impiegato in secondi per calcolare : " + elapsedseconds);

	
	
}
}
