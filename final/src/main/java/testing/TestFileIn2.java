package testing;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import org.json.*;

public class TestFileIn2 {

	public static void main(String[] args) {
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(
					new FileInputStream("HotelList.json"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
		System.out.println("Read file?");
		System.out.println(fileIn.hasNextLine());
		StringBuffer tmp = new StringBuffer("");
		int i = 0;
		while (fileIn.hasNextLine() & i<10) {
			tmp.append(fileIn.nextLine());
			System.out.print(fileIn.nextLine());
			i++;
		}
		fileIn.close();
		
		System.out.println(tmp);
//		String hotelJSON = new String(tmp);
//		System.out.println(hotelJSON);
//		JSONArray obj = new JSONArray(hotelJSON);
//		
//		System.out.println(obj.length());
//		
//		JSONObject hotel0 = obj.getJSONObject(0);
//		System.out.println(hotel0.getInt("HotelID"));
//		System.out.println(hotel0.getString("Street-Address"));
//		
//		JSONObject hotel1 = obj.getJSONObject(1);
//		System.out.println(hotel1.getInt("HotelID"));
//		System.out.println(hotel1.getString("Street-Address"));
	}

}
