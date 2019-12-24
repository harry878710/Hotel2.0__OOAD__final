package hotelAndRoom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class HotelList {

	public static final Hotel[] ALLHOTEL;
	
	static {
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(
					new FileInputStream("HotelList.json"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
		StringBuffer tmp = new StringBuffer("");
		while (fileIn.hasNextLine()) {
			tmp.append(fileIn.nextLine());
		}
		fileIn.close();
		String hotelString = new String(tmp);
		JSONArray obj = new JSONArray(hotelString);
		
		ALLHOTEL = new Hotel[1500];
		
		for (int i = 0; i < ALLHOTEL.length; i++) {
			
			JSONObject hotelJSON = obj.getJSONObject(i);
			ALLHOTEL[i] = new Hotel(hotelJSON.getInt("HotelID"), hotelJSON.getInt("HotelStar"),
					hotelJSON.getString("Locality"), hotelJSON.getString("Street-Address"));
		}
	}
}
