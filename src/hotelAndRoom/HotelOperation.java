package hotelAndRoom;

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
	 * " ADDRESS        CHAR(50), " + " LANDLORD           TEXT    NOT NULL) ";
	 * stmt.executeUpdate(sql); stmt.close(); c.close(); } catch ( Exception e ) {
	 * System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	 * System.exit(0); } System.out.println("Table created successfully"); }
	 */

	// Create the room database

	/*
	 * static { Connection c = null; Statement stmt = null; try {
	 * Class.forName("org.sqlite.JDBC"); c =
	 * DriverManager.getConnection("jdbc:sqlite:room.db");
	 * System.out.println("Opened database successfully");
	 * 
	 * stmt = c.createStatement(); String sql = "CREATE TABLE ROOM " +
	 * "(KEY TEXT PRIMARY KEY UNIQUE ,HOTELID INT  NOT NULL," +
	 * " TYPE      TEXT    NOT NULL, " + " PRICE       INT    NOT NULL, " +
	 * " NUMBER    INT    NOT NULL) "; stmt.executeUpdate(sql); stmt.close();
	 * c.close(); } catch (Exception e) { System.err.println(e.getClass().getName()
	 * + ": " + e.getMessage()); System.exit(0); }
	 * System.out.println("Table created successfully"); }
	 */

	// Load data from json to hotel.db
	/*
	 * static { Scanner fileIn = null; try { fileIn = new Scanner(new
	 * FileInputStream("HotelList.json")); } catch (FileNotFoundException e) {
	 * System.out.println("File not found."); System.exit(0); } StringBuffer tmp =
	 * new StringBuffer(""); while (fileIn.hasNextLine()) {
	 * tmp.append(fileIn.nextLine()); } fileIn.close(); String hotelString = new
	 * String(tmp); JSONArray obj = new JSONArray(hotelString); for (int i = 0; i <
	 * obj.length(); i++) { JSONObject hotelJSON = obj.getJSONObject(i);
	 * addHotelToDB(hotelJSON.getInt("HotelID"), hotelJSON.getInt("HotelStar"),
	 * hotelJSON.getString("Locality"), hotelJSON.getString("Street-Address"), 0); }
	 * }
	 */

	// Load data from json to room.db
	static {
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new FileInputStream("HotelList.json"));
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
		for (int i = 0; i < 1500; i++) {
			JSONObject hotelJSON = obj.getJSONObject(i);
			for (int j = 0; j < 3; j++) {
				JSONObject hotelRoom = hotelJSON.getJSONArray("Rooms").getJSONObject(j);
				addRoomToDB(hotelJSON.getInt("HotelID"), hotelRoom.getString("RoomType"), hotelRoom.getInt("RoomPrice"),
						hotelRoom.getInt("Number"));
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		addHotelToDB(2,5,"桃園","永安北路456巷789號10樓",0);
//		addRoomToDB(50,"Single",588,15);
//		System.out.println(sizeOfHotelList());
	}

	public static void addHotelToDB(int hotelID, int star, String locality, String address, int landlordID) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO HOTEL (ID,STAR,LOCALITY,ADDRESS,LANDLORD) " + "VALUES (" + hotelID + "," + star
					+ ", '" + locality + "','" + address + "'," + landlordID + ");";
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

	public static void addRoomToDB(int hotelID, String type, int price, int number) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:room.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			int key;
			switch (type) {
			case "Single": key = 1;break;
			case "Double": key = 2; break;
			case "Quad" : key = 4 ; break;
			default: key = 0;break;
			}
			String sql = "INSERT INTO ROOM (KEY,HOTELID,TYPE,PRICE,NUMBER) "
			+ "VALUES ('" +hotelID+"."+key+"',"+ hotelID + ", '" + type + "',"
					+ price + "," + number + ");";
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

	// not yet done...
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
				int landlordID = rs.getInt("LANDLORD");
//				newHotelList[i]=new Hotel(hotelID,star,locality,address);
				i++;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return newHotelList;
	}

}
