package hotelAndRoom;

public class HotelList {

	public static final Hotel[] ALLHOTEL;
	public static int TOTAL_NUMBER_OF_HOTEL = 1500;

	static {
		ALLHOTEL = HotelOperation.uploadHotelList();
		TOTAL_NUMBER_OF_HOTEL = HotelOperation.sizeOfHotelList();
		/*
		 * Scanner fileIn = null; try { fileIn = new Scanner( new
		 * FileInputStream("HotelList.json")); } catch (FileNotFoundException e) {
		 * System.out.println("File not found."); System.exit(0); } StringBuffer tmp =
		 * new StringBuffer(""); while (fileIn.hasNextLine()) {
		 * tmp.append(fileIn.nextLine()); } fileIn.close(); String hotelString = new
		 * String(tmp); JSONArray obj = new JSONArray(hotelString);
		 * 
		 * ALLHOTEL = new Hotel[1500];
		 * 
		 * for (int i = 0; i < ALLHOTEL.length; i++) {
		 * 
		 * JSONObject hotelJSON = obj.getJSONObject(i); ALLHOTEL[i] = new
		 * Hotel(hotelJSON.getInt("HotelID"), hotelJSON.getInt("HotelStar"),
		 * hotelJSON.getString("Locality"), hotelJSON.getString("Street-Address")); }
		 */
		
	}
}
