package testing;
import org.json.*;

public class TestJSON {

	public static void main(String[] args) {
		String tmp = 
				"[{\r\n" + 
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
				"  }," +
				"{\r\n" + 
				"    \"HotelID\": 1,\r\n" + 
				"    \"HotelStar\": 5,\r\n" + 
				"    \"Locality\": \"台北泰\",\r\n" + 
				"    \"Street-Address\": \"民生東路一段38號\",\r\n" + 
				"    \"Rooms\": [\r\n" + 
				"      {\r\n" + 
				"        \"RoomType\": \"Single\",\r\n" + 
				"        \"RoomPrice\": 5188,\r\n" + 
				"        \"Number\": 829\r\n" + 
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
				"  }]"
				;
		JSONArray obj = new JSONArray(tmp);

		JSONObject hotel0 = obj.getJSONObject(0);
		System.out.println(hotel0.getInt("HotelID"));
		System.out.println(hotel0.getString("Street-Address"));
		
		JSONObject hotel1 = obj.getJSONObject(1);
		System.out.println(hotel1.getInt("HotelID"));
		System.out.println(hotel1.getString("Street-Address"));
	}

}
