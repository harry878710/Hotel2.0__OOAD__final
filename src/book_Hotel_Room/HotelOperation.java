package book_Hotel_Room;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class HotelOperation {

	// Create the hotel database
	/*
	 * static { Connection c = null; Statement stmt = null; try {
	 * Class.forName("org.sqlite.JDBC"); c =
	 * DriverManager.getConnection("jdbc:sqlite:hotel.db");
	 * System.out.println("Opened database successfully");
	 * 
	 * stmt = c.createStatement(); String sql = "CREATE TABLE HOTEL " +
	 * "(ID INT PRIMARY KEY UNIQUE    NOT NULL," +
	 * " STAR           TEXT    NOT NULL, " + " LOCALITY       TEXT    NOT NULL, " +
	 * " ADDRESS        CHAR(50), " +
	 * "NUMBER1 INT NOT NULL, NUMBER2 INT NOT NULL, NUMBER4 INT NOT NULL," +
	 * "PRICE1 INT NOT NULL,PRICE2 INT NOT NULL, PRICE4 INT NOT NULL," +
	 * " LANDLORD           TEXT    NOT NULL) "; stmt.executeUpdate(sql);
	 * stmt.close(); c.close(); } catch (Exception e) {
	 * System.err.println(e.getClass().getName() + ": " + e.getMessage());
	 * System.exit(0); } System.out.println("Table created successfully"); }
	 */

	// Load data from initial json to hotel.db
	/*
	 * static { Scanner fileIn = null; try { fileIn = new Scanner(new
	 * FileInputStream("HotelList.json")); } catch (FileNotFoundException e) {
	 * System.out.println("File not found."); System.exit(0); } StringBuffer tmp =
	 * new StringBuffer(""); while (fileIn.hasNextLine()) {
	 * tmp.append(fileIn.nextLine()); } fileIn.close(); String hotelString = new
	 * String(tmp); JSONArray obj = new JSONArray(hotelString); for (int i = 0; i <
	 * obj.length(); i++) { JSONObject hotelJSON = obj.getJSONObject(i); JSONObject
	 * hotelRoom1 = hotelJSON.getJSONArray("Rooms").getJSONObject(0); JSONObject
	 * hotelRoom2 = hotelJSON.getJSONArray("Rooms").getJSONObject(1); JSONObject
	 * hotelRoom4 = hotelJSON.getJSONArray("Rooms").getJSONObject(2); int[]
	 * roomCombination = { hotelRoom1.getInt("Number"), hotelRoom2.getInt("Number"),
	 * hotelRoom4.getInt("Number") }; int[] roomPrice = {
	 * hotelRoom1.getInt("RoomPrice"), hotelRoom2.getInt("RoomPrice"),
	 * hotelRoom4.getInt("RoomPrice") }; addHotelToDB(hotelJSON.getInt("HotelID"),
	 * hotelJSON.getInt("HotelStar"), hotelJSON.getString("Locality"),
	 * hotelJSON.getString("Street-Address"), roomCombination, roomPrice, 0); } }
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void addHotelToDB(int star, String locality, String address, int[] roomCombination, int[] roomPrice,
			String landlordID) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO HOTEL (ID,STAR,LOCALITY,ADDRESS,NUMBER1,NUMBER2,NUMBER4,PRICE1,PRICE2,PRICE4,LANDLORD) "
					+ "VALUES (" + nextHotelId() + "," + star + ", '" + locality + "','" + address + "',"
					+ roomCombination[0] + "," + roomCombination[1] + "," + roomCombination[2] + "," + roomPrice[0]
					+ "," + roomPrice[1] + "," + roomPrice[2] + ",'" + landlordID + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void addHotelToDB(Hotel hotel) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO HOTEL (ID,STAR,LOCALITY,ADDRESS,NUMBER1,NUMBER2,NUMBER4,PRICE1,PRICE2,PRICE4,LANDLORD) "
					+ "VALUES (" + nextHotelId() + "," + hotel.getStar() + ", '" + hotel.getLocality() + "','"
					+ hotel.getAddress() + "'," + hotel.getRoomCombination()[0] + "," + hotel.getRoomCombination()[1]
					+ "," + hotel.getRoomCombination()[2] + "," + hotel.getRoomInfo()[0].getPrice() + ","
					+ hotel.getRoomInfo()[1].getPrice() + "," + hotel.getRoomInfo()[0].getPrice() + ","
					+ hotel.getLandlord() + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static String showThisHotel(int hotelId) {
		String toReturn = "The book ID is not existed.";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM hotel;");
			while (rs.next()) {
				// (ID,STAR,LOCALITY,ADDRESS,NUMBER1,NUMBER2,NUMBER4,PRICE1,PRICE2,PRICE4,LANDLORD)
				if (rs.getInt("ID") == hotelId) {
					int[] roomCombination = { rs.getInt("NUMBER1"), rs.getInt("NUMBER2"), rs.getInt("NUMBER4") };
					int[] price = { rs.getInt("PRICE1"), rs.getInt("PRICE2"), rs.getInt("PRICE4") };
					toReturn = ("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nRooms and prices:"
							+ "\nSingle room number: " + roomCombination[0] + ", price: " + price[0]
							+ "\nDouble room number: " + roomCombination[1] + ", price: " + price[1]
							+ "\nQuad   room number: " + roomCombination[2] + ", price: " + price[2] + "\n");
					break;
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return toReturn;
		// System.out.println("Operation done successfully");

	}

	public static int sizeOfHotelList() {
		Connection c = null;
		Statement stmt = null;
		int size = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM HOTEL;");
			while (rs.next()) {
				size++;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return size;
	}

	public static int nextHotelId() {
		int hotelId = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM hotel;");
			while (rs.next()) {
				int thisId = rs.getInt("ID");
				hotelId = (thisId > hotelId) ? thisId : hotelId;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return hotelId + 1;
	}

	public static Hotel[] uploadHotelList() {
		int newTotalNumberOfHotel = sizeOfHotelList();
		Hotel[] newHotelList = new Hotel[newTotalNumberOfHotel];
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM HOTEL;");
			int i = 0;
			while (rs.next() && (i < newTotalNumberOfHotel)) {
				int hotelID = rs.getInt("ID");
				int star = rs.getInt("STAR");
				String locality = rs.getString("LOCALITY");
				String address = rs.getString("ADDRESS");
				String landlord = rs.getString("LANDLORD");
				int[] roomCombination = { rs.getInt("NUMBER1"), rs.getInt("NUMBER2"), rs.getInt("NUMBER4") };
				Room[] roomInfo = new Room[3];
				roomInfo[0] = new Room("Single", rs.getInt("PRICE1"));
				roomInfo[1] = new Room("Double", rs.getInt("PRICE2"));
				roomInfo[2] = new Room("Quad", rs.getInt("PRICE4"));
				newHotelList[i] = new Hotel(hotelID, star, locality, address, roomCombination, roomInfo, landlord);
				i++;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		HotelList.TOTAL_NUMBER_OF_HOTEL = newTotalNumberOfHotel;
		return newHotelList;
	}

}