package com.mercury.businesstime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UtilityClass {
	static int WORKINGDAYHOURS;

	public UtilityClass(int workingdayhours) {
		WORKINGDAYHOURS = workingdayhours;
	}

	public long slacalculatorSeconds(Date startDate, Date endDate, String[] businessHours) {
		// ritorno D H M S
		BusinessTimeCalculator wtc = new BusinessTimeCalculator(startDate, endDate, 0, businessHours);
		long deltaseconds = wtc.getSeconds();

		return deltaseconds;
	}

	public long slacalculatorMinute(Date startDate, Date endDate, String[] businessHours) {
		// ritorno D H M
		BusinessTimeCalculator wtc2 = new BusinessTimeCalculator(startDate, endDate, 0, businessHours);
		long deltaminute = (wtc2.getMinutes());

		return deltaminute;

	}

	/* Converte un long in una stringa data con D H M */
	public String timeConvert2(int minutes) {
		int Days = minutes / WORKINGDAYHOURS / 60;
		int Hours = minutes / 60 % WORKINGDAYHOURS;
		int Minutes = minutes % 60;

		return "W_Days " + Days + ", W_Hours " + Hours + ", Min " + Minutes;
	}

	/* Converte un long in una stringa data con D H M S */
	public String timeConvert3(long seconds) {
		long s = seconds % 60;
		long m = (seconds / 60) % 60;
		long h = (seconds / (60 * 60)) % WORKINGDAYHOURS;
		long d = (seconds / 60) / WORKINGDAYHOURS / 60;

		return "W_Days " + d + ", W_Hours " + h + ", Min " + m + ", Sec " + s;
	}

	/* Date parser */
	public Date createDate(String data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'");
		// format.setTimeZone(TimeZone.getTimeZone("UTC"));
		format.setTimeZone(TimeZone.getDefault());
		Date dt = null;
		try {
			dt = format.parse(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dt;
	}
}
