package bookAndUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




public class UserOperation { 

	// Note:
	// At the first time you use this class, you should un-toggle the static{ }
	// below,
	// to make sure the table is created.

//	static {
//		Connection c = null;
//		Statement stmt = null;
//		try {
//			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:user.db");
//			System.out.println("Opened database successfully");
//			stmt = c.createStatement();
//			String sql = "CREATE TABLE USER " + "(ID TEXT PRIMARY KEY UNIQUE    NOT NULL,"
//					+ " PASSWORD           TEXT    NOT NULL)";
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
		UserOperation.addUser("raiyray", "hoho", "hoho");

	}

	// Add an user with corresponding id and password.
	// If the id has been used, it would cause an exception.
	// We can turn it to a pop-up window later.
	public static int addUser(String id, String password, String checkPass) {
		if (!password.equals(checkPass)) {
			return 1;
		}
		if (id.equals("") || password.equals("") || checkPass.equals("")) {
			return 3;
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:user.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO USER (ID,PASSWORD) " + "VALUES ('" + id + "','" + password + "');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();

		} catch (org.sqlite.SQLiteException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return 2;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return 0;
		// System.out.println("Records created successfully");

	}

	public static int userLogin(String userId,String password) {
		if (!userId.equals("") && !password.equals("")) {
			if (UserOperation.hasUser(userId)) {
				if (UserOperation.checkPassword(userId, password)) {
					for (int i = 0; i < UserList.userList.size(); i++) {
						if (userId.equals(UserList.userList.get(i).getName())) {
							//find specified user in userList,change his login status.
							UserList.userList.get(i).setLogin(true);
							return 0;
						} 
					}
				} else {
					//wrong password.
					return 1;
				}
			} else {
				//no such specified user in userList.
				return 2;
			}
		} else {
			//try to login without fill all the blanks
			return 3;
		}
		return 4;
	}
	// To show you all the user.
	// Actually, you can use DB Browser to see all the user, too.
	public static void showAllUser() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:user.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
			while (rs.next()) {
				String id = rs.getString("ID");
				String password = rs.getString("PASSWORD");

				System.out.println("ID = " + id);
				System.out.println("PASSWORD = " + password);
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

	// To change a user's password.
	// If the user Id is not exist, it would print a msg.
	// If the old password is wrong, it would print a msg, too.
	public static int changePassword(String id, String oldPass, String newPass,String repeat) {
		if (!hasUser(id)) {
			System.out.println("The user id is not exist.");
			return -1;
		}
		if(id.equals("")||oldPass.equals("")||newPass.equals("")||repeat.equals("")) {
			return 3;
		}
		if(!newPass.equals(repeat)) {
			return 2;
		}
		if(newPass.equals(oldPass)) {
			return 4;
		}
		int toReturn = -1;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:user.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
			while (rs.next()) {
				String tmpId = rs.getString("ID");
				String tmpPass = rs.getString("PASSWORD");
				if (tmpId.equals(id)) {
					if (tmpPass.equals(oldPass)) {
						String sql = "UPDATE USER set PASSWORD = '" + newPass + "' where ID='" + id + "';";
						stmt.executeUpdate(sql);
						c.commit();
						System.out.println("Changed password successfully.");
						toReturn = 0;
					} else {
						System.out.println("Your old password is wrong.");
						toReturn = 1;
					}
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

	// Check if the password is right.
	// If the id is not exist or the password is wrong, it would return false.
	// Only if the password is right would it return true.
	public static boolean checkPassword(String id, String password) {
		if (!hasUser(id)) {
			return false;
		}
		boolean toReturn = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:user.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
			while (rs.next()) {
				String tmpId = rs.getString("ID");
				String tmpPass = rs.getString("PASSWORD");
				if (tmpId.equals(id)) {
					if (tmpPass.equals(password)) {
						toReturn = true;
					} else {
						// System.out.println("Your password is wrong.");
						toReturn = false;
					}
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
		return toReturn;
	}

	public static boolean hasUser(String id) {
		boolean get = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:user.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
			while (rs.next()) {
				if (rs.getString("ID").equals(id)) {
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
		// System.out.println("Operation done successfully");
	}

	public static boolean anyoneLoggedin() {
		for (int i = 0; i < UserList.userList.size(); i++) {
			if (UserList.userList.get(i).isLogin()) {
				return true;
			}
		}
		return false;
	}

	public static void everyOneloggedOut() {
		for (int i = 0; i < UserList.userList.size(); i++) {
			UserList.userList.get(i).setLogin(false);
		}
	}

	public static String whoIsLoggedin() {
		for (int i = 0; i < UserList.userList.size(); i++) {
			if (UserList.userList.get(i).isLogin()) {
				return UserList.userList.get(i).getName();
			}
		}
		return null;
	}

	public static ArrayList<String> usersOrders(String userName) {
		ArrayList<String> bookIds = new ArrayList<String>();
		for (int i = 0; i < BookList.bookList.size(); i++) {
			if (BookList.bookList.get(i).getUserId().equals(userName)) {
				bookIds.add(BookList.bookList.get(i).getBookId());
			}
		}
		return bookIds;
	}
	
	public static ArrayList<User> uploadUserList() {
		ArrayList<User> userList = new ArrayList<User>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:user.db");
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
			while (rs.next()) {
				String name = rs.getString("Id");
				String password = rs.getString("Password");

				User user = new User(name, password);
				userList.add(user);

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return userList;
	}

}
