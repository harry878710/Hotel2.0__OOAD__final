package bookAndUser;

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

//	static {
//		Connection c = null;
//		Statement stmt = null;
//		try {
//			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:book.db");
//			System.out.println("Opened database successfully");
//			stmt = c.createStatement();
//			String sql = "CREATE TABLE BOOK " + "(BookId TEXT PRIMARY KEY UNIQUE    NOT NULL,"
//					+ " UserId   TEXT    NOT NULL, CheckInDate TEXT    NOT NULL, CheckOutDate TEXT    NOT NULL,"
//					+ "HotelId INT NOT NULL, Single INT NOT NULL,Double INT NOT NULL,Quad INT NOT NULL)";
//			stmt.executeUpdate(sql);
//			stmt.close();
//			c.close();
//		} catch (Exception e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			System.exit(0);
//		}
//		System.out.println("Table created successfully");
//	}

	public static void main(String[] args) {
		addBook("00003", "hong", "2019/06/30", "2019/07/01", 99, 2, 0, 0);
		showBook("00001");

	}

	public static ArrayList<String> bookIdListOfUser(String userId) {
		ArrayList<String> bookIdList = new ArrayList<String>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("UserId").equals(userId)) {
					bookIdList.add(rs.getString("BookId"));
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
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("UserId").equals(userId)) {
					String bookId = rs.getString("BookId");
					String checkInDate = rs.getString("CheckInDate");
					String checkOutDate = rs.getString("CheckOutDate");
					int hotelId = rs.getInt("HotelId");
					int single = rs.getInt("Single");
					int doub = rs.getInt("Double");
					int quad = rs.getInt("Quad");
					int stayNight = 0;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					Date bookDate = sdf.parse(checkInDate, new ParsePosition(0));
					while (!bookDate.equals(sdf.parse(checkOutDate, new ParsePosition(0)))) {
						stayNight += 1;
						bookDate = nextDate(bookDate);
					}
					int price = 0;
					price += RoomList.ALLROOM[hotelId][0].getPrice() * single;
					price += RoomList.ALLROOM[hotelId][1].getPrice() * doub;
					price += RoomList.ALLROOM[hotelId][2].getPrice() * quad;
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
	public static void addBook(String bookId, String userId, String checkInDate, String checkOutDate, int hotelId,
			int single, int doub, int quad) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO book (BookId,UserId,CheckInDate,CheckOutDate,HotelId,Single,Double,Quad)"
					+ "VALUES ('" + bookId + "','" + userId + "','" + checkInDate + "','" + checkOutDate + "','"
					+ hotelId + "','" + single + "','" + doub + "','" + quad + "');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Records created successfully");

	}

	// To show you all the user.
	// Actually, you can use DB Browser to see all the user, too.
	public static void showAllBook() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String bookId = rs.getString("BookId");
				String userId = rs.getString("UserId");
				String checkInDate = rs.getString("CheckInDate");
				String checkOutDate = rs.getString("CheckOutDate");
				int hotelId = rs.getInt("HotelId");
				int single = rs.getInt("Single");
				int doub = rs.getInt("Double");
				int quad = rs.getInt("Quad");
				int stayNight = 0;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date bookDate = sdf.parse(checkInDate, new ParsePosition(0));
				while (!bookDate.equals(sdf.parse(checkOutDate, new ParsePosition(0)))) {// 嚙豌多嚙踝蕭
					stayNight += 1;
					bookDate = nextDate(bookDate); // checkInDate嚙罵嚙踝蕭嚙罵嚙瞋嚙踝掉???
				}
				int price = 0;
				price += RoomList.ALLROOM[hotelId][0].getPrice() * single;
				price += RoomList.ALLROOM[hotelId][1].getPrice() * doub;
				price += RoomList.ALLROOM[hotelId][2].getPrice() * quad;
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
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("BookId").equals(bookId)) {
					String userId = rs.getString("UserId");
					String checkInDate = rs.getString("CheckInDate");
					String checkOutDate = rs.getString("CheckOutDate");
					int hotelId = rs.getInt("HotelId");
					int single = rs.getInt("Single");
					int doub = rs.getInt("Double");
					int quad = rs.getInt("Quad");
					int stayNight = 0;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					Date bookDate = sdf.parse(checkInDate, new ParsePosition(0));
					while (!bookDate.equals(sdf.parse(checkOutDate, new ParsePosition(0)))) {// 嚙豌多嚙踝蕭
						stayNight += 1;
						bookDate = nextDate(bookDate); // checkInDate嚙罵嚙踝蕭嚙罵嚙瞋嚙踝掉???
					}
					int price = 0;
					price += RoomList.ALLROOM[hotelId][0].getPrice() * single;
					price += RoomList.ALLROOM[hotelId][1].getPrice() * doub;
					price += RoomList.ALLROOM[hotelId][2].getPrice() * quad;
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
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("BookId").equals(bookId)) {
					String userId = rs.getString("UserId");
					String checkInDate = rs.getString("CheckInDate");
					String checkOutDate = rs.getString("CheckOutDate");
					int hotelId = rs.getInt("HotelId");
					int[] roomCombination = {rs.getInt("Single"),rs.getInt("Double"),rs.getInt("Quad")};
					int stayNight = 0;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					Date bookDate = sdf.parse(checkInDate, new ParsePosition(0));
					while (!bookDate.equals(sdf.parse(checkOutDate, new ParsePosition(0)))) {// 嚙豌多嚙踝蕭
						stayNight += 1;
						bookDate = nextDate(bookDate); // checkInDate嚙罵嚙踝蕭嚙罵嚙瞋嚙踝掉???
					}
					int price = 0;
					price += RoomList.ALLROOM[hotelId][0].getPrice() * roomCombination[0];
					price += RoomList.ALLROOM[hotelId][1].getPrice() * roomCombination[1];
					price += RoomList.ALLROOM[hotelId][2].getPrice() * roomCombination[2];
					tmpBook = new Book(hotelId, roomCombination,price,new SearchAndBook().stringToDate(checkInDate), stayNight, bookId, userId);
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

	public static void changeBook(String bookId, int newSingle, int newDouble, int newQuad) {
		if (!hasBook(bookId)) {
			System.out.println("The book ID is not existed.");
			return;
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String tmpId = rs.getString("BookId");
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

	public static void changeBook(String bookId, String checkInDate, String checkOutDate) {
		if (!hasBook(bookId)) {
			System.out.println("The book ID is not existed.");
			return;
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String tmpId = rs.getString("BookId");
				if (tmpId.equals(bookId)) {
					String sql = "UPDATE BOOK set CheckInDate = '" + checkInDate + "',CheckOutDate = '" + checkOutDate
							+ "' where BookId='" + bookId + "';";
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
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
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
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				if (rs.getString("BookId").equals(bookId)) {
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
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String bookId = rs.getString("BookId");
				String userId = rs.getString("UserId");
				String checkInDate = rs.getString("CheckInDate");
				String checkOutDate = rs.getString("CheckOutDate");
				int hotelId = rs.getInt("HotelId");
				int single = rs.getInt("Single");
				int doub = rs.getInt("Double");
				int quad = rs.getInt("Quad");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = sdf.parse(checkInDate, new ParsePosition(0));
				while (!date.equals(sdf.parse(checkOutDate, new ParsePosition(0)))) {// 嚙豌多嚙踝蕭
					for (int i = single; i > 0; i--) {// 嚙豌多嚙踝蕭
						Book book = new Book(date, RoomList.ALLROOM[hotelId][0], bookId, userId);
						bookList.add(book);
					}
					for (int i = doub; i > 0; i--) {// 嚙豌多嚙踝蕭
						Book book = new Book(date, RoomList.ALLROOM[hotelId][1], bookId, userId);
						bookList.add(book);
					}
					for (int i = quad; i > 0; i--) {// 嚙豌多嚙踝蕭
						Book book = new Book(date, RoomList.ALLROOM[hotelId][2], bookId, userId);
						bookList.add(book);
					}
					date = nextDate(date); // checkInDate嚙罵嚙踝蕭嚙罵嚙瞋嚙踝掉???
				}
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

	private static Date nextDate(Date thisDate) {
		// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
		Calendar c = Calendar.getInstance();
		c.setTime(thisDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		return nextDate;
	}

	public static int uploadBookedNumber() {
		int bookedId = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:book.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
			while (rs.next()) {
				String bookId = rs.getString("BookId");
				int id = new Integer(bookId);
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

}
