package bookAndUser;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import hotelAndRoom.*;
import operation.*;

public class BookOperation {

	// Note:
	// At the first time you use this class, you should un-toggle the static{ }
	// below,
	// to make sure the table is created.

	/*
	 * static { Connection c = null; Statement stmt = null; try {
	 * Class.forName("org.sqlite.JDBC"); c =
	 * DriverManager.getConnection("jdbc:sqlite:book.db");
	 * System.out.println("Opened database successfully"); stmt =
	 * c.createStatement(); String sql = "CREATE TABLE BOOK " +
	 * "(BookId TEXT PRIMARY KEY UNIQUE    NOT NULL," +
	 * "HotelId INT NOT NULL, Single INT NOT NULL,Double INT NOT NULL,Quad INT NOT NULL,"
	 * +
	 * " Price INT NOT NULL, Night INT NOT NULL, CheckInDate TEXT    NOT NULL, CheckOutDate TEXT    NOT NULL,"
	 * + "UserId   TEXT    NOT NULL)"; stmt.executeUpdate(sql); stmt.close();
	 * c.close(); } catch (Exception e) { System.err.println(e.getClass().getName()
	 * + ": " + e.getMessage()); System.exit(0); }
	 * System.out.println("Table created successfully"); }
	 */

	private static final String url = "jdbc:postgresql://140.112.73.145/book_postgre";
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
		addBook("00111", 149, 2, 3, 1, 5004, 2, "01/04/2020", "01/06/2020", "rayray");
	}

	public static ArrayList<String> bookIdListOfUser(String userId) {
		ArrayList<String> bookIdList = new ArrayList<String>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("userid").equals(userId)) {
					bookIdList.add(rs.getString("bookid"));
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return bookIdList;
	}

	public static String bookOfUser(String userId) {
		StringBuffer tmp = new StringBuffer("");
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("userid").equals(userId)) {
					String bookId = rs.getString("bookid");
					String checkInDate = rs.getString("checkinDate");
					String checkOutDate = rs.getString("checkoutdate");
					int hotelId = rs.getInt("hotelid");
					int price = rs.getInt("price");
					int single = rs.getInt("single");
					int doub = rs.getInt("double");
					int quad = rs.getInt("quad");
					int stayNight = rs.getInt("night");
					tmp.append("Book ID: " + bookId + "\n" + HotelList.ALLHOTEL[hotelId].toString() + "\n"
							+ "Check-in date: " + checkInDate + ", Check-out date: " + checkOutDate + "\n"
							+ "Stay nights: " + stayNight + " nights, Total price: " + price + "\n" + "Room:\n Single: "
							+ single + "\n Double: " + doub + "\n Quad: " + quad
							+ "\n=================================\n");
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		String toReturn = new String(tmp);
		return toReturn;
	}

	// Add an user with corresponding id and password.
	// If the id has been used, it would cause an exception.
	// We can turn it to a pop-up window later.
	// (BookId,HotelId,Single,Double,Quad,Price,Night,CheckInDate,CheckOutDate,UserId)
	public static void addBook(String bookId, int hotelId, int single, int doub, int quad, int price, int night,
			String checkInDate, String checkOutDate, String userId) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO book (BookId,HotelId,Single,Double,Quad,Price,Night,CheckInDate,CheckOutDate,UserId)"
					+ "VALUES ('" + bookId + "'," + hotelId + "," + single + "," + doub + "," + quad + "," + price + ","
					+ night + ",'" + checkInDate + "','" + checkOutDate + "','" + userId + "');";
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

	// To show you all the user.
	// Actually, you can use DB Browser to see all the user, too.
	public static void showAllBook() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String bookId = rs.getString("bookid");
				String userId = rs.getString("userid");
				String checkInDate = rs.getString("checkindate");
				String checkOutDate = rs.getString("checkoutdate");
				int hotelId = rs.getInt("hotelid");
				int single = rs.getInt("single");
				int doub = rs.getInt("double");
				int quad = rs.getInt("quad");
				int stayNight = rs.getInt("night");
				int price = rs.getInt("price");
				System.out.println(
						"Book ID: " + bookId + ", User ID: " + userId + "\n" + HotelList.ALLHOTEL[hotelId].toString()
								+ "\n" + "Check-in date: " + checkInDate + ", Check-out date: " + checkOutDate + "\n"
								+ "Stay nights: " + stayNight + " nights, Total price: " + price + "\n"
								+ "Room:\n	Single: " + single + "\n	Double: " + doub + "\n	Quad: " + quad);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");

	}

	public static void showBook(String bookId) {
		if (!hasBook(bookId)) {
			System.out.println("The book ID is not existed.");
			return;
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("bookid").equals(bookId)) {
					String userId = rs.getString("userid");
					String checkInDate = rs.getString("checkindate");
					String checkOutDate = rs.getString("checkoutdate");
					int hotelId = rs.getInt("hotelid");
					int single = rs.getInt("single");
					int doub = rs.getInt("double");
					int quad = rs.getInt("quad");
					int stayNight = rs.getInt("night");
					int price = rs.getInt("price");
					System.out.println("Book ID: " + bookId + ", User ID: " + userId + "\n"
							+ HotelList.ALLHOTEL[hotelId].toString() + "\n" + "Check-in date: " + checkInDate
							+ ", Check-out date: " + checkOutDate + "\n" + "Stay nights: " + stayNight
							+ " nights, Total price: " + price + "\n" + "Room:\n	Single: " + single
							+ "\n	Double: " + doub + "\n	Quad: " + quad);
					System.out.println();
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
		// System.out.println("Operation done successfully");

	}

	public static Book getBook(String bookId) {
		if (!hasBook(bookId)) {
			return null;
		}
		Book tmpBook = null;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("bookid").equals(bookId)) {
					String userId = rs.getString("userid");
					String checkInDate = rs.getString("checkindate");
					String checkOutDate = rs.getString("checkoutdate");
					int hotelId = rs.getInt("hotelId");
					int[] roomCombination = { rs.getInt("single"), rs.getInt("double"), rs.getInt("quad") };
					int stayNight = rs.getInt("night");
					int price = rs.getInt("price");
					tmpBook = new Book(hotelId, roomCombination, stringToDate(checkInDate), stayNight, bookId, userId);
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return tmpBook;
		// System.out.println("Operation done successfully");

	}

	public static void changeBook(String bookId, int newSingle, int newDouble, int newQuad, int price) {
		if (!hasBook(bookId)) {
			System.out.println("The book ID is not existed.");
			return;
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String tmpId = rs.getString("bookid");
				if (tmpId.equals(bookId)) {
					String sql = "";
					sql = "UPDATE BOOK set Single = '" + newSingle + "' where BookId='" + bookId + "';";
					stmt.executeUpdate(sql);
					c.commit();
					sql = "UPDATE BOOK set Double = '" + newDouble + "' where BookId='" + bookId + "';";
					stmt.executeUpdate(sql);
					c.commit();
					sql = "UPDATE BOOK set Quad = '" + newQuad + "' where BookId='" + bookId + "';";
					stmt.executeUpdate(sql);
					c.commit();
					sql = "UPDATE BOOK set Price = '" + price + "' where BookId='" + bookId + "';";
					stmt.executeUpdate(sql);
					c.commit();
					System.out.println("Changed book successfully.");
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
		// System.out.println("Operation done successfully");
	}

	public static void changeBook(String bookId, int night, String checkInDate, String checkOutDate, int price) {
		if (!hasBook(bookId)) {
			System.out.println("The book ID is not existed.");
			return;
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String tmpId = rs.getString("bookid");
				if (tmpId.equals(bookId)) {
					String sql = "UPDATE BOOK set Night = '" + night + "',CheckInDate = '" + checkInDate
							+ "',CheckOutDate = '" + checkOutDate + "',Price = '" + price + "' where BookId='" + bookId
							+ "';";
					stmt.executeUpdate(sql);
					c.commit();
					System.out.println("Changed book successfully.");
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
		// System.out.println("Operation done successfully");
	}

	public static void deleteBook(String bookId) {
		if (!hasBook(bookId)) {
			System.out.println("The book ID is not existed.");
			return;
		}

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DELETE from BOOK where BookId='" + bookId + "';";
			stmt.executeUpdate(sql);
			c.commit();

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
	}

	public static boolean hasBook(String bookId) {
		Connection c = null;
		Statement stmt = null;
		boolean get = false;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("bookid").equals(bookId)) {
					get = true;
				}
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return get;
	}

	public static ArrayList<Book> uploadBookList() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String bookId = rs.getString("bookid");
				String userId = rs.getString("userid");
				String checkInDate = rs.getString("checkindate");
				String checkOutDate = rs.getString("checkoutdate");
				int night = rs.getInt("night");
				int hotelId = rs.getInt("hotelid");
				int[] roomCombination = { rs.getInt("single"), rs.getInt("double"), rs.getInt("quad") };
				int price = rs.getInt("price");
				Book newBook = new Book(hotelId, roomCombination, stringToDate(checkInDate), night, bookId, userId);
				bookList.add(newBook);

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return bookList;
	}

	public static int uploadBookedNumber() {
		int bookedId = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String bookId = rs.getString("bookid");
				int id = Integer.parseInt(bookId);
				bookedId = (id > bookedId) ? id : bookedId;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return bookedId + 1;
	}

	private static Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(str, new ParsePosition(0));
	}

}
