package testing;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class TestString {
	public static void main(String[] args) {
		
		System.out.println(dateToString(calculateCheckOutDate(stringToDate("12/31/2019"), 2)));
		
	}
	private static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}

	private static Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(str, new ParsePosition(0));
	}

	private static Date calculateCheckOutDate(Date checkInDate, int night) {
		Date toReturn = new Date(checkInDate.getTime());
		for (int i = 0; i < night; i++) {
			toReturn = nextDate(toReturn);
		}
		return toReturn;
	}

	private static Date nextDate(Date thisDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(thisDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		return nextDate;
	}
}
