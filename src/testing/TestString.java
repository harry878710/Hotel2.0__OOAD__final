package testing;

import java.util.StringTokenizer;

public class TestString {
	public String s = "[\r\n" + 
			"  {\r\n" + 
			"    \"HotelID\": 0,\r\n" + 
			"    \"HotelStar\": 2,\r\n" + 
			"    \"Locality\": \"台北\",\r\n" + 
			"    \"Street-Address\": \"民生東路一段28號\",\r\n" + 
			"    \"Rooms\": [\r\n" + 
			"      {\r\n" + 
			"        \"RoomType\": \"Single\",\r\n" + 
			"        \"RoomPrice\": 518,\r\n" + 
			"        \"Number\": 29\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"RoomType\": \"Double\",\r\n" + 
			"        \"RoomPrice\": 1251,\r\n" + 
			"        \"Number\": 21\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"RoomType\": \"Quad\",\r\n" + 
			"        \"RoomPrice\": 2122,\r\n" + 
			"        \"Number\": 13\r\n" + 
			"      }\r\n" + 
			"    ]\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"HotelID\": 1,\r\n" + 
			"    \"HotelStar\": 4,\r\n" + 
			"    \"Locality\": \"台北\",\r\n" + 
			"    \"Street-Address\": \"中山北路四段1號\",\r\n" + 
			"    \"Rooms\": [\r\n" + 
			"      {\r\n" + 
			"        \"RoomType\": \"Single\",\r\n" + 
			"        \"RoomPrice\": 1869,\r\n" + 
			"        \"Number\": 12\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"RoomType\": \"Double\",\r\n" + 
			"        \"RoomPrice\": 3052,\r\n" + 
			"        \"Number\": 26\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"RoomType\": \"Quad\",\r\n" + 
			"        \"RoomPrice\": 4399,\r\n" + 
			"        \"Number\": 11\r\n" + 
			"      }\r\n" + 
			"    ]\r\n" + 
			"  } ]";
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer(new TestString().s);
		StringBuffer sb = new StringBuffer("");
		while(st.hasMoreTokens()) {
			sb.append(" "+st.nextToken());
		}
		String str = new String(sb);
		System.out.println(str);
		
	}
}
