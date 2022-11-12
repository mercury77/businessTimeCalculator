package com.mercury.businesstime;



public class Application {
	public static long counter = 0;

	public static void main(String[] args) {
//		String[] date = { "2019-10-15T14:52:21.000Z", "2019-10-15T14:59:21.000Z", // strano in prod conta 7 minuti
//																					// esatti
//				"2019-10-15T15:04:33.000Z", "2019-10-15T15:04:36.000Z",
//				"2019-10-15T15:13:15.000Z","2019-10-15T15:13:19.000Z",
//				"2019-10-15T15:14:20.000Z", "2019-10-15T15:14:31.000Z",
//				"2019-10-15T15:14:58.000Z", "2019-10-15T15:15:33.000Z", 
//				"2019-10-15T15:15:33.000Z",	"2019-10-15T15:15:38.000Z",
//				"2019-10-15T15:16:09.000Z", "2019-10-15T15:17:26.000Z",
//				"2019-10-15T15:17:35.000Z", "2019-10-15T15:17:43.000Z",
//				"2019-10-15T15:20:36.000Z",	"2019-10-15T15:35:17.000Z",
//				"2019-10-15T16:10:08.000Z", "2019-10-29T10:34:19.000Z",
//				"2019-10-29T10:35:29.000Z", "2019-10-29T10:35:47.000Z",
//				"2019-10-30T10:04:41.000Z", "2019-11-30T10:04:41.000Z",
//				"2020-02-14T15:23:38.000Z", "2020-02-14T22:42:55.000Z" };
//
//		// 10.630.800 secondi iterazioni possibili 10.630.800 semplice DATEDIFF
//		// algoritmo esclude sab - dom e solo 08:00 alle 18:00 ogni giorno al massimo
//		// 60*60*10 => 36.000 secondi / iterazioni
//		// calcolo complessità numero iterazioni => 4.032.000 reali => tempo impiegato 9
//		// secondi !
//
//	//	String[] date1 = { "2019-10-15T14:52:21.000Z", "2020-02-15T14:52:22.000Z" }; // il giorno 15 febbraio è un
//																						// sabato !!!
//
//		/* MYIVECO 9100 */
//
//	//	String[] date2 = { "2019-09-02T11:04:12.000Z", "2019-09-02T11:15:59.000Z", "2019-09-04T09:09:14.000Z",
//				"2019-09-04T09:14:01.000Z", "2019-09-04T09:14:33.000Z", "2019-09-10T11:16:36.000Z",
//				"2019-09-11T14:51:49.000Z", "2019-09-11T14:52:33.000Z", "2019-09-11T17:06:19.000Z",
//				"2019-10-01T11:46:58.000Z", "2019-10-01T15:49:06.000Z", "2019-10-01T18:20:28.000Z",
//				"2019-10-04T11:06:27.000Z", "2019-10-04T11:23:02.000Z", "2019-12-12T11:25:26.000Z",
//				"2019-12-12T16:00:56.000Z", "2019-12-19T09:55:07.000Z", "2019-12-19T21:03:25.000Z",
//				"2020-02-05T16:10:52.000Z" };
//
////		String date3[] = { "2020-02-14T18:30:01.000Z", "2020-02-18T10:15:30.000Z" };
//// date4[] = { "2019-10-14T11:55:19.000Z", "2020-02-24T11:08:00.000Z" };
//		String date5[] = { "2019-07-29T08:00:34.000Z", "2020-02-29T11:00:34.000Z" };
//		// String date6[] = {"2020-03-14T10:00:00.000Z","2020-03-16T10:01:00.000Z"};
//		// String[] date6 = {"2020-03-14T17:08:37.000Z","2020-03-22T21:07:00.000Z"};
//		// String date6[] = {"2020-02-16T18:30:00.000Z","2020-02-17T08:01:13.000Z"};
//		// String[] date6 = {"2020-03-02T08:00:00.000Z","2020-03-03T18:00:00.000Z"};
//		// String date6[] =
//		// {"2020-02-02T13:42:48.000Z","2020-02-03T09:11:55.000Z","2020-02-03T11:37:53.000Z"};
//		/* Expected 4 but returns 5 */
//
//		/* FINE ESEMPI E DATE PER FARE I TEST S */
//		/* USARE QUESTO BUILDER PER FARE OPERAZIONI I PARAMETRI MANDATORY è SOLO DATES GLI ALTRI POSSONO ESSERE OMESSI */
		OperationAttributes attributi = new OperationAttributeBuilder()
				.withDates(new String[] {"2020-03-02T09:00:00.000Z","2020-03-02T18:00:00.000Z",
						                 "2022-11-07T09:00:00.000Z","2022-11-09T18:00:00.000Z"})
				.withBusinessHours(new String[] { "09:00:00.000", "18:00:00.000" })
				.withPrecision(true)
				.withDayHours(9)
				.createAttribute();
		
		
		EntryClass ec = new EntryClass(attributi);
		ec.executeCalc();
		
		
		
	}

}
