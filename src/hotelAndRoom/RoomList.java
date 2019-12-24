package hotelAndRoom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.*;

public class RoomList {
	public static final Room[][] ALLROOM;
	public static final int[][] totalRoomNumber;

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

		ALLROOM = new Room[1500][3];
		totalRoomNumber = new int[1500][3];
		for (int i = 0; i < ALLROOM.length; i++) {
			JSONObject hotelJSON = obj.getJSONObject(i);
			Hotel hotel = new Hotel(hotelJSON.getInt("HotelID"), hotelJSON.getInt("HotelStar"),
					hotelJSON.getString("Locality"), hotelJSON.getString("Street-Address"));
			for (int j = 0; j < ALLROOM[i].length; j++) {
				JSONObject hotelRoom = hotelJSON.getJSONArray("Rooms").getJSONObject(j);
				ALLROOM[i][j] = new Room(hotelRoom.getString("RoomType"), hotelRoom.getInt("RoomPrice"), hotel);
				totalRoomNumber[i][j] = hotelRoom.getInt("Number");
			}
		}
	}

}
