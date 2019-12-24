package testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.util.TimeZone;
//import java.util.Calendar;
import java.util.Date;
//import java.util.Locale;
import java.text.ParsePosition;

public class TestTime {

	public static void main(String[] args) {
		// 取得現在時間
		long currentTime = System.currentTimeMillis();
		Date thisDay = new Date(currentTime);
		System.out.println(thisDay);
		// Instant now = Instant.now();

		// TimeZone tz = TimeZone.getTimeZone("Asia/Taipei");
		// Locale.TAIWAN;
		// GMT +08:00;
		// CST
		// new SimpleDateFormat().setTimeZone(tz);

		SimpleDateFormat sdf0 = new SimpleDateFormat();// use default Locale
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");

		// ParsePosition p = new ParsePosition(0);
		Date d = sdf1.parse("2019-05-20", new ParsePosition(0));
		System.out.println(sdf0.parse("2019/5/20 上午 00:01", new ParsePosition(0)));
		System.out.println(sdf1.parse("2019-05-08", new ParsePosition(0)));
		System.out.println(sdf2.parse("2019/12/20", new ParsePosition(0)));
		System.out.println(sdf2.parse("2019/5/2", new ParsePosition(0)));

		// String s = sdf.format(thisDay);
		System.out.println(sdf0.format(thisDay));
		System.out.println(sdf1.format(thisDay));
		System.out.println(sdf2.format(thisDay));

	}

}
