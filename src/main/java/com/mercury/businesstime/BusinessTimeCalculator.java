package com.mercury.businesstime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * A utility class to calculate working minutes/hours/days between two given dates unlike the simple difference
 * between two dates.
 * @since 0.0.1
 * @auhor Shashank Agrawal
 * @ppirani getSeconds() Time precision and loop optimizations
 * forked on GitHub commit b2843t1493473y92847342j
 */
public class BusinessTimeCalculator {

	private Date startDate, endDate, dayStartTime, dayEndTime;

	private Calendar startCal = Calendar.getInstance();
	private Calendar endCal = Calendar.getInstance();

	private DateFormat timeParser = new SimpleDateFormat("HH:mm:ss.sss");

	private Integer weekendDay1 = Calendar.SUNDAY;
	private Integer weekendDay2 = Calendar.SATURDAY;
	private int minutes = 0;
	private long seconds = 0;
	private int SODH = 0;
	private int SODM = 0;
	private int workingMinutes;

	/* Constructor */
	public BusinessTimeCalculator(Date startDate, Date endDate, int offset, String[] businessHours) {
		this.startDate = startDate;
		this.endDate = endDate;

		startCal.setTime(startDate);
		endCal.setTime(endDate);

		setWorkingTime(businessHours[0], businessHours[1]);
		minutes = offset;
		String[] splitted = businessHours[0].split(":");
		SODH = (splitted[0].substring(0, 0).equals("0")) ? Integer.parseInt(splitted[0].substring(1, 1))
				: Integer.parseInt(splitted[0].substring(0, 2));
		SODM = (splitted[1].substring(0, 0).equals("0")) ? Integer.parseInt(splitted[1].substring(1, 1))
				: Integer.parseInt(splitted[1].substring(0, 2));

	}

	/**
	 * Helper method to verify whether the weekends passed by the method
	 * {#setWeekends setWeekends()} is one of the 7 days.
	 * 
	 * @param weekendDay The Calendar integer number of the supplied weekend day
	 * @throws IllegalArgumentException if the supplied weekend integer is not a
	 *                                  valid day
	 */
	private void validateWeekend(Integer weekendDay) {
		// If weekend day value is not Mon, Tue, Wed, Thurs, Fri, Sat or Sunday
		if (weekendDay != null && (weekendDay < Calendar.SUNDAY || weekendDay > Calendar.SATURDAY)) {
			throw new IllegalArgumentException("Invalid weekend day selected");
		}
	}

	/**
	 * Internal helper method to log for debugging.
	 * 
	 * @param data
	 */

	/**
	 * In the {#getMinutes getMinutes()} method we are increasing the {#startDate}
	 * (passed by constructor) minute by minute up to it matches the {#endDate}.
	 * This helper method checks whether the {#startCal} is in the working hour and
	 * is not in the weekend.
	 *
	 * @return <code>true</code> if the {#startDate} is in the working hour of
	 *         weekdays.
	 */
	private Boolean isDuringWorkingHour() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		calendar1.setTime(dayStartTime);
		calendar2.setTime(dayEndTime);

		List<Integer> fields = Arrays.asList(Calendar.YEAR, Calendar.MONTH, Calendar.DATE);

		for (int field : fields) {
			calendar1.set(field, startCal.get(field));
			calendar2.set(field, startCal.get(field)); // ?? BOH questa non la capisco onestamente
		}

		// Get the work start time of the date
		Date date1 = calendar1.getTime();
		// Get the work end time of the date
		Date date2 = calendar2.getTime();
		Date date = startCal.getTime();

		if (date1.before(startDate)) {
			date1 = startDate;
		}

		// If the work end time is greater than the endDate
		if (date2.after(endDate)) {
			// Then set it to the endDate to stop calculating further and also fix invalid
			// calculation
			date2 = endDate;
		}

		// Consider the start time as inclusive and end date as exclusive
		Boolean status = (date.equals(date1) || date.after(date1)) && date.before(date2);
		// System.out.println("" + date1 + " > " + date + " < " + date2 + " " + status);
		return status;
	}

	/**
	 * @return Get number of working time seconds between two given dates.
	 */
	public long getSeconds() {

		while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
			int day = startCal.get(Calendar.DAY_OF_WEEK);
			if ((weekendDay1 != null && day == weekendDay1) || (weekendDay2 != null && day == weekendDay2)) {
				startCal.add(Calendar.DAY_OF_MONTH, 1);
				startCal.set(Calendar.HOUR_OF_DAY, SODH);
				startCal.set(Calendar.MINUTE, SODM);
				startCal.set(Calendar.SECOND, 0);
				continue;
			}
			if (!isDuringWorkingHour()) {
				startCal.add(Calendar.SECOND, 1);

				continue;
			}
			seconds++;
			Application.counter++;

			startCal.add(Calendar.SECOND, 1);
		}

		return seconds;
	}

	public Integer getMinutes() {

		while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
			int day = startCal.get(Calendar.DAY_OF_WEEK);
			if ((weekendDay1 != null && day == weekendDay1) || (weekendDay2 != null && day == weekendDay2)) {
				startCal.add(Calendar.DAY_OF_MONTH, 1);

				startCal.set(Calendar.HOUR_OF_DAY, SODH);
				startCal.set(Calendar.MINUTE, SODM);

				continue;
			}

			if (!isDuringWorkingHour()) {
				startCal.add(Calendar.MINUTE, 1);

				continue;
			}

			minutes++;
			startCal.add(Calendar.MINUTE, 1);
		}

		return (minutes == -1) ? 0 : minutes; // FIX 54
	}

	public String getHoursMinutes() {
		// TODO Optimize this loop
		while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
			int day = startCal.get(Calendar.DAY_OF_WEEK);
			if ((weekendDay1 != null && day == weekendDay1) || (weekendDay2 != null && day == weekendDay2)) {
				startCal.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}

			if (!isDuringWorkingHour()) {
				startCal.add(Calendar.MINUTE, 1);
				continue;
			}

			minutes++;
			startCal.add(Calendar.MINUTE, 1);
		}
		int hours = minutes / 60; // since both are ints, you get an int
		int minute = minutes % 60;
		// int seconds = minutes / 3600;

		return String.format("%02d:%02d", hours, minute);
	}

	/**
	 * @return Get number of working days between two given dates.
	 */
	public Double getDays() {
		getMinutes();
		return ((double) minutes / workingMinutes);
	}

	/**
	 * Set start time of the working day and end time of the working day.
	 * 
	 * @param startTime Work start time in HH:mm format. (Default 09:30)
	 * @param endTime   Work end time in HH:mm format. (Default 16:30)
	 */
	public void setWorkingTime(String startTime, String endTime) {
		try {
			this.dayStartTime = timeParser.parse(startTime);
			this.dayEndTime = timeParser.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}

		this.workingMinutes = (int) ((this.dayEndTime.getTime() - this.dayStartTime.getTime()) / (1000 * 60));
	}

	/**
	 * Set weekend days to exclude. Any of the fields or both can be null.
	 * 
	 * @param weekendDay1 Weekend day first like Calendar.SATURDAY (default)
	 * @param weekendDay2 Weekend day second like Calendar.SUNDAY (default)
	 */
	public void setWeekends(Integer weekendDay1, Integer weekendDay2) {
		validateWeekend(weekendDay1);
		validateWeekend(weekendDay2);

		this.weekendDay1 = weekendDay1;
		this.weekendDay2 = weekendDay2;
	}
}