package testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.time.Instant;
//import java.util.TimeZone;
//import java.util.Calendar;
import java.util.Date;
//import java.util.Locale;
import java.text.ParsePosition;

public class TestTime {

	public static void main(String[] args) {
		// ���o�{�b�ɶ�
//		long currentTime = System.currentTimeMillis();
//		Date thisDay = new Date(currentTime);
//		System.out.println(thisDay);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		System.out.println(dateFormat.format(today));
				
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date checkIn = sdf.parse("12/30/2019", new ParsePosition(0));
		System.out.println(dateFormat.format(checkIn));
		System.out.println(today.after(checkIn));
		
		// Instant now = Instant.now();
		// TimeZone tz = TimeZone.getTimeZone("Asia/Taipei");
		// Locale.TAIWAN;
		// GMT +08:00;
		// CST
		// new SimpleDateFormat().setTimeZone(tz);

//		SimpleDateFormat sdf0 = new SimpleDateFormat();// use default Locale
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
//
//		// ParsePosition p = new ParsePosition(0);
//		Date d = sdf1.parse("2019-05-20", new ParsePosition(0));
//		System.out.println(sdf0.parse("2019/5/20 �W�� 00:01", new ParsePosition(0)));
//		System.out.println(sdf1.parse("2019-05-08", new ParsePosition(0)));
//		System.out.println(sdf2.parse("2019/12/20", new ParsePosition(0)));
//		System.out.println(sdf2.parse("2019/5/2", new ParsePosition(0)));

		// String s = sdf.format(thisDay);
//		System.out.println(sdf0.format(thisDay));
//		System.out.println(sdf1.format(thisDay));
//		System.out.println(sdf2.format(thisDay));

	}

}
