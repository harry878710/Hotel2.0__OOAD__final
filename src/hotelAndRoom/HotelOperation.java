package hotelAndRoom;

import java.sql.SQLException;
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

import bookAndUser.Book;

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
	private static final String url = "jdbc:postgresql://140.112.77.36/hotel_postgre";
	private static final String user = "postgres";
	private static final String passwords = "harry8787";

	/**
	 * Connect to the PostgreSQL database
	 *
	 * @return a Connection object
	 */
	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwords);
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void addHotelToDB(int hotelID, int star, String locality, String address, int[] roomCombination,
			int[] roomPrice, int landlordID) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO HOTEL (ID,STAR,LOCALITY,ADDRESS,NUMBER1,NUMBER2,NUMBER4,PRICE1,PRICE2,PRICE4,LANDLORD) "
					+ "VALUES (" + hotelID + "," + star + ", '" + locality + "','" + address + "'," + roomCombination[0]
					+ "," + roomCombination[1] + "," + roomCombination[2] + "," + roomPrice[0] + "," + roomPrice[1]
					+ "," + roomPrice[2] + "," + landlordID + ");";
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
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO HOTEL (ID,STAR,LOCALITY,ADDRESS,NUMBER1,NUMBER2,NUMBER4,PRICE1,PRICE2,PRICE4,LANDLORD) "
					+ "VALUES (" + hotel.getId() + "," + hotel.getStar() + ", '" + hotel.getLocality() + "','"
					+ hotel.getAddress() + "'," + hotel.getRoomCombination()[0] + "," + hotel.getRoomCombination()[1]
					+ "," + hotel.getRoomCombination()[2] + "," + hotel.getRoomInfo()[0].getPrice() + ","
					+ hotel.getRoomInfo()[1].getPrice() + "," + hotel.getRoomInfo()[0].getPrice() + "," + 0 + ");";
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

	public static int sizeOfHotelList() {
		Connection c = null;
		Statement stmt = null;
		int size = 0;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

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
		System.out.println("Operation done successfully");
		return size;
	}

	public static Hotel[] uploadHotelList() {
		int newTotalNumberOfHotel = sizeOfHotelList();
		Hotel[] newHotelList = new Hotel[newTotalNumberOfHotel];
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM HOTEL;");
			int i = 0;
			while (rs.next() && (i < newTotalNumberOfHotel)) {
				int hotelID = rs.getInt("ID");
				int star = rs.getInt("STAR");
				String locality = rs.getString("LOCALITY");
				String address = rs.getString("ADDRESS");
				int[] roomCombination = { rs.getInt("NUMBER1"), rs.getInt("NUMBER2"), rs.getInt("NUMBER4") };
				Room[] roomInfo = new Room[3];
				roomInfo[0] = new Room("Single", rs.getInt("PRICE1"));
				roomInfo[1] = new Room("Double", rs.getInt("PRICE2"));
				roomInfo[2] = new Room("Quad", rs.getInt("PRICE4"));
				newHotelList[i] = new Hotel(hotelID, star, locality, address, roomCombination, roomInfo);
				i++;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return newHotelList;
	}

}
